# バックエンドアーキテクチャ

## 目的

本ドキュメントは、WorkHub のバックエンド設計方針を定義する。

WorkHub のバックエンドは、**Java + Spring Boot 3** を用いて実装する。

本プロジェクトでは、Java/Spring Boot の実務的な設計力を身につけるため、最初から **ミニマムなオニオンアーキテクチャ** を採用する。

---

## 技術スタック

| 分類        | 技術                |
| --------- | ----------------- |
| 言語        | Java 21           |
| フレームワーク   | Spring Boot 3     |
| Web       | Spring MVC        |
| ORM       | Spring Data JPA   |
| DB        | MySQL             |
| Migration | Flyway            |
| 認証        | Spring Security   |
| API仕様     | SpringDoc OpenAPI |
| テスト       | JUnit5 / Mockito  |
| ビルド       | Gradle            |
| コンテナ      | Docker            |

---

## アーキテクチャ方針

WorkHub では、以下の4層でバックエンドを構成する。

```text
presentation
    ↓
application
    ↓
domain

infrastructure
    ↓
domain
```

依存関係は常に内側である `domain` に向かう。

`domain` は他レイヤーに依存しない。

---

## レイヤー構成

```text
backend/src/main/java/com/example/workhub/
├── presentation/
│   ├── controller/
│   ├── request/
│   ├── response/
│   └── mapper/
│
├── application/
│   ├── usecase/
│   └── command/
│
├── domain/
│   ├── model/
│   ├── valueobject/
│   └── repository/
│
└── infrastructure/
    ├── persistence/
    │   ├── entity/
    │   ├── repository/
    │   └── mapper/
    └── config/
```

---

## presentation層

### 責務

* HTTPリクエストを受け取る
* Request DTOへ変換する
* 入力値バリデーションを行う
* Application層のUseCaseを呼び出す
* Response DTOを返す

### 配置

```text
presentation/
├── controller/
├── request/
├── response/
└── mapper/
```

### ルール

* Controllerに業務ロジックを書かない
* ControllerからRepositoryを直接呼ばない
* ControllerからInfrastructure層を直接呼ばない
* ControllerはUseCaseを呼び出すだけにする

---

## application層

### 責務

* ユースケースを実現する
* トランザクション境界を管理する
* Domainモデルを操作する
* Domain Repository Interfaceを通じて永続化を依頼する

### 配置

```text
application/
├── usecase/
└── command/
```

### UseCase例

```text
application/usecase/project/
├── CreateProjectUseCase.java
├── UpdateProjectUseCase.java
├── DeleteProjectUseCase.java
├── GetProjectUseCase.java
└── GetProjectListUseCase.java
```

### ルール

* UseCaseは1ユースケース1クラスを基本とする
* UseCaseにHTTPの知識を持たせない
* UseCaseにJPAの知識を持たせない
* UseCaseはDomainモデルとRepository Interfaceを利用する

---

## domain層

### 責務

* 業務ルールを表現する
* Entityを定義する
* Value Objectを定義する
* Repository Interfaceを定義する

### 配置

```text
domain/
├── model/
├── valueobject/
└── repository/
```

### ルール

* Domainは他レイヤーに依存しない
* Domain EntityはRequest/Response/JPA Entityを知らない
* 業務ルールは可能な限りDomainに寄せる
* Getter/Setterだけの貧血モデルにしない

---

## infrastructure層

### 責務

* DBアクセスを実装する
* JPA Entityを定義する
* Spring Data JPA Repositoryを定義する
* Domain Repository Interfaceを実装する
* DomainとJPA Entityの変換を行う

### 配置

```text
infrastructure/
├── persistence/
│   ├── entity/
│   ├── repository/
│   └── mapper/
└── config/
```

### ルール

* InfrastructureはDomainに依存してよい
* InfrastructureはApplicationを呼び出さない
* JPA EntityをDomain層へ漏らさない
* DB都合の実装はInfrastructure層に閉じ込める

---

## Mapper方針

WorkHubではMapperを使用する。

ただし、Domain層にMapperは置かない。

### 配置

```text
presentation/mapper/
infrastructure/persistence/mapper/
```

### 変換責務

| 変換                  | 配置                                   |
| ------------------- | ------------------------------------ |
| Request → Command   | `presentation/mapper/`               |
| Domain → Response   | `presentation/mapper/`               |
| JPA Entity → Domain | `infrastructure/persistence/mapper/` |
| Domain → JPA Entity | `infrastructure/persistence/mapper/` |

### 重要ルール

```text
Domain EntityはDTOやJPA Entityを知らない
```

---

## Transaction方針

トランザクションはApplication層のUseCaseで管理する。

```java
@Transactional
public ProjectResponse execute(CreateProjectCommand command) {
    ...
}
```

### ルール

* Controllerに `@Transactional` を付けない
* Repository実装に業務単位のトランザクションを持たせない
* 参照系は必要に応じて `@Transactional(readOnly = true)` を使用する

---

## API設計方針

APIはRESTを基本とする。

詳細は以下を参照する。

```text
docs/api/index.md
```

### MVP対象API

* Auth
* Project
* Task
* WorkLog

---

## 認証方針

MVPではSpring SecurityによるSession認証を採用する。

Nuxt4からJSON形式でログインするため、Spring Security標準のフォームログインではなく、API用のログインエンドポイントを実装する。

MVPでは学習を優先し、CSRFは一旦無効化する。

本番構成では以下を再検討する。

* CSRF Cookie方式
* SameSite属性
* CORS設定
* JWT認証
* Refresh Token

---

## 例外処理方針

例外処理は共通化する。

```text
presentation/exception/
```

### 方針

* Controllerごとにtry-catchを書かない
* `@RestControllerAdvice` を使用する
* エラーレスポンスはRFC7807 Problem Detailsを基本とする
* バリデーションエラーも共通形式で返す

---

## Logger方針

`System.out.println()` は使用しない。

ログ出力にはSLF4Jを使用する。

### ログレベル

| レベル   | 用途              |
| ----- | --------------- |
| DEBUG | 開発時の詳細確認        |
| INFO  | 正常な業務イベント       |
| WARN  | 異常ではないが注意が必要な状態 |
| ERROR | 例外・処理失敗         |

### 注意事項

* パスワードをログに出さない
* 個人情報を不用意にログに出さない
* TokenやSession IDをログに出さない

---

## テスト方針

### Unit Test

* Domain
* UseCase

### Integration Test

* Repository
* Controller

### 使用技術

* JUnit5
* Mockito
* MockMvc
* Testcontainers

---

## 命名方針

### UseCase

```text
CreateProjectUseCase
UpdateProjectUseCase
DeleteProjectUseCase
GetProjectUseCase
GetProjectListUseCase
```

### Command

```text
CreateProjectCommand
UpdateProjectCommand
```

### Request

```text
CreateProjectRequest
UpdateProjectRequest
```

### Response

```text
ProjectResponse
ProjectListResponse
```

### JPA Entity

```text
ProjectJpaEntity
TaskJpaEntity
WorkLogJpaEntity
```

---

## 開発方針

* 最初から完璧なDDDを目指さない
* ミニマムなオニオンアーキテクチャで実装する
* FactoryやDomainServiceは必要になるまで作らない
* ValueObjectは効果が高いものから導入する
* まずはMVP完成を最優先にする
* 設計に迷った場合は、複雑さより完遂を優先する
