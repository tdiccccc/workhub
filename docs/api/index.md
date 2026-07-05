# API エンドポイント定義

## 概要

本API仕様書は完成形(v2以降)ではなく、**Spring Boot実践カリキュラム(v1.0)** の実装対象として利用する。

完遂を優先するため、MVP(Minimum Viable Product)のスコープに合わせて整理する。

- ベースURL: `http://localhost:8200/api`
- 対象フレームワーク: Spring Boot 3
- レスポンス形式: JSON
- API仕様生成: SpringDoc OpenAPI

## 実装スコープ

### MVP実装対象

- 認証
- Project
- Task
- WorkLog

### 将来実装予定

以下は削除ではなく、v2以降または学習フェーズ後半で実装する。

- Category
- Users
- Roles
- Reports
- Slack
- Backlog

---

## 認証方式

### Phase1

- Spring Security
- Session認証

Nuxt4からJSON形式でログインするため、Spring Securityの標準フォームログインではなく、API用のログインエンドポイントを用意する。

MVPでは学習を優先し、CSRFは一旦無効化する。

本番構成ではCSRF Cookie方式、SameSite属性、CORS設定を再検討する。

### Phase2

- JWT認証

### 将来

- OAuth2
- Google Login

### 認証構成のロードマップ

| バージョン | 認証方式 |
|------------|----------|
| v1 | Session |
| v2 | JWT |
| v3 | Refresh Token |
| v4 | OAuth2 |

---

## 共通仕様

### HTTPステータスコード

| コード | 意味 | 使用ケース |
|--------|------|------------|
| 200 | OK | 成功(GET, PUT) |
| 201 | Created | リソース作成成功(POST) |
| 204 | No Content | 削除成功(DELETE) |
| 400 | Bad Request | リクエスト不正、バリデーションエラー |
| 401 | Unauthorized | 認証エラー |
| 403 | Forbidden | 権限エラー |
| 404 | Not Found | リソースが存在しない |
| 500 | Internal Server Error | サーバーエラー |

### エラーレスポンス形式

レスポンスはRFC7807(Problem Details)を採用予定。

Spring Boot 3標準に合わせる。

```json
{
  "type": "...",
  "title": "...",
  "status": 400,
  "detail": "...",
  "instance": "/api/tasks"
}
```

### ページネーション

検索、ソート、ページネーションはv0.5で実装する。

MVP初期では必要最小限の一覧取得を優先し、リスト取得APIでは将来的に以下のクエリパラメータをサポートする。

- `page`: ページ番号
- `size`: 1ページあたりの件数
- `sort`: ソート条件

---

## Controller設計

Controllerは薄く保つ。

```text
Controller
  ↓
Service
  ↓
Repository
```

責務を明確に分離する。

---

## OpenAPI

Swagger仕様書を手書きではなく、SpringDoc OpenAPIから自動生成する。

```text
springdoc-openapi
```

を採用する。

---

## 認証 API

Session認証を前提とする。

Nuxt4からJSON形式でログインするため、`POST /api/login` はSpring Security標準のフォームログインではなく、API用のカスタムログインエンドポイントとして実装する。

### POST /api/login

ログイン

**リクエスト**

```json
{
  "email": "user@example.com",
  "password": "password"
}
```

**レスポンス (200)**

```json
{
  "id": 1,
  "name": "山田太郎",
  "email": "user@example.com"
}
```

### POST /api/logout

ログアウト(認証必須)

**レスポンス (204)**

### GET /api/me

ログイン中のユーザー情報取得(認証必須)

**レスポンス (200)**

```json
{
  "id": 1,
  "name": "山田太郎",
  "email": "user@example.com"
}
```

---

## Project API

JSONでは `active` を使用する。

Java側のフィールド名は `private boolean active;` を基本とし、`isActive` はGetter名やJSON変換で混乱しやすいため避ける。

### GET /api/projects

プロジェクト一覧取得

**レスポンス (200)**

```json
{
  "data": [
    {
      "id": 1,
      "name": "案件A",
      "amount": 1000000,
      "description": "案件Aの概要",
      "startedAt": "2024-01-01",
      "endedAt": null,
      "active": true,
      "createdAt": "2024-01-01T00:00:00",
      "updatedAt": "2024-01-01T00:00:00"
    }
  ]
}
```

### GET /api/projects/{projectId}

プロジェクト詳細取得

**レスポンス (200)**

```json
{
  "id": 1,
  "name": "案件A",
  "amount": 1000000,
  "description": "案件Aの概要",
  "startedAt": "2024-01-01",
  "endedAt": null,
  "active": true,
  "createdAt": "2024-01-01T00:00:00",
  "updatedAt": "2024-01-01T00:00:00"
}
```

### POST /api/projects

プロジェクト作成

**リクエスト**

```json
{
  "name": "案件A",
  "amount": 1000000,
  "description": "案件Aの概要",
  "startedAt": "2024-01-01",
  "active": true
}
```

**レスポンス (201)**

### PUT /api/projects/{projectId}

プロジェクト更新

**リクエスト**

```json
{
  "name": "案件A(更新)",
  "amount": 1200000,
  "description": "案件Aの概要を更新",
  "startedAt": "2024-01-01",
  "endedAt": "2024-12-31",
  "active": false
}
```

**レスポンス (200)**

### DELETE /api/projects/{projectId}

プロジェクト削除

**レスポンス (204)**

---

## Task API

TaskはProject配下のリソースとして扱う。

理由:

- 親子関係が明確
- REST設計として自然
- Projectとの関連を毎回意識できる

### GET /api/projects/{projectId}/tasks

プロジェクト配下のタスク一覧取得

**レスポンス (200)**

```json
{
  "data": [
    {
      "id": 1,
      "projectId": 1,
      "name": "データベース設計",
      "description": "ER図の作成",
      "status": "IN_PROGRESS",
      "estimatedHours": 8,
      "dueDate": "2024-01-15",
      "createdAt": "2024-01-01T00:00:00",
      "updatedAt": "2024-01-05T00:00:00"
    }
  ]
}
```

### POST /api/projects/{projectId}/tasks

タスク作成

**リクエスト**

```json
{
  "name": "データベース設計",
  "description": "ER図の作成",
  "status": "OPEN",
  "estimatedHours": 8,
  "dueDate": "2024-01-15"
}
```

**レスポンス (201)**

### GET /api/projects/{projectId}/tasks/{taskId}

タスク詳細取得

**レスポンス (200)**

```json
{
  "id": 1,
  "projectId": 1,
  "name": "データベース設計",
  "description": "ER図の作成",
  "status": "IN_PROGRESS",
  "estimatedHours": 8,
  "actualHours": 6.5,
  "dueDate": "2024-01-15",
  "createdAt": "2024-01-01T00:00:00",
  "updatedAt": "2024-01-05T00:00:00"
}
```

### PUT /api/projects/{projectId}/tasks/{taskId}

タスク更新

**リクエスト**

```json
{
  "name": "データベース設計(更新)",
  "description": "ER図の作成を完了する",
  "status": "DONE",
  "estimatedHours": 8,
  "dueDate": "2024-01-15"
}
```

**レスポンス (200)**

### DELETE /api/projects/{projectId}/tasks/{taskId}

タスク削除

**レスポンス (204)**

---

## WorkLog API

WorkLogはTask配下のリソースとして扱う。

ただし、`GET /api/work-logs` は「自分の工数一覧」として残す。

MVPではURLを短く保つため、作業ログAPIは `/api/tasks/{taskId}/work-logs` を採用する。

より厳密にProject配下まで表現する場合は、将来的に `/api/projects/{projectId}/tasks/{taskId}/work-logs` への変更を検討する。

### GET /api/work-logs

自分の工数一覧取得(認証必須)

**レスポンス (200)**

```json
{
  "data": [
    {
      "id": 1,
      "taskId": 1,
      "workDate": "2024-01-05",
      "workMinutes": 240,
      "comment": "ER図作成中",
      "createdAt": "2024-01-05T18:00:00",
      "updatedAt": "2024-01-05T18:00:00"
    }
  ]
}
```

### GET /api/tasks/{taskId}/work-logs

タスク配下の作業ログ一覧取得

**レスポンス (200)**

```json
{
  "data": [
    {
      "id": 1,
      "taskId": 1,
      "workDate": "2024-01-05",
      "workMinutes": 240,
      "comment": "ER図作成中",
      "createdAt": "2024-01-05T18:00:00",
      "updatedAt": "2024-01-05T18:00:00"
    }
  ]
}
```

### POST /api/tasks/{taskId}/work-logs

作業ログ記録

**リクエスト**

```json
{
  "workDate": "2024-01-05",
  "workMinutes": 240,
  "comment": "ER図作成中"
}
```

**レスポンス (201)**

### GET /api/tasks/{taskId}/work-logs/{id}

作業ログ詳細取得

**レスポンス (200)**

```json
{
  "id": 1,
  "taskId": 1,
  "workDate": "2024-01-05",
  "workMinutes": 240,
  "comment": "ER図作成中",
  "createdAt": "2024-01-05T18:00:00",
  "updatedAt": "2024-01-05T18:00:00"
}
```

### PUT /api/tasks/{taskId}/work-logs/{id}

作業ログ更新

**リクエスト**

```json
{
  "workDate": "2024-01-05",
  "workMinutes": 300,
  "comment": "ER図作成完了"
}
```

**レスポンス (200)**

### DELETE /api/tasks/{taskId}/work-logs/{id}

作業ログ削除

**レスポンス (204)**

---

## v1では実装しないAPI

### User API

v1では不要。

Spring Security導入前に管理画面まで実装すると学習範囲が広がりすぎるため、初期ユーザーはアプリケーション画面からではなくDB初期データとして作成する。

初期データはFlywayのmigration SQLで投入する。

開発初期の簡易構成では `data.sql` も候補とするが、基本はFlywayを優先する。

実装タイミングはSpring Security以降とする。

### Role API

v1では不要。

Spring Securityの認証、認可を学ぶフェーズで追加する。

### Reports API

後回し。

以下を学習してから実装した方が理解しやすい。

- JPQL
- GROUP BY
- 集計SQL
- QueryDSL
- Specification

### Slack API

今回スコープ外。

今回の目的はSpring BootによるWebシステム開発であり、外部API連携ではない。

v2以降で追加する。

### Backlog API

今回スコープ外。

v2以降で追加する。

### Category API

MVPからは外す。

Task Categoryはv0.4で追加する。

---

## API仕様のバージョン管理

完成版を一気に実装しない。

GitHub Milestone単位で実装する。

| Milestone | 実装内容 |
|-----------|----------|
| v0.1 | 認証、Project CRUD |
| v0.2 | Task CRUD |
| v0.3 | WorkLog CRUD |
| v0.4 | Task Category |
| v0.5 | 検索、ソート、ページネーション |
| v0.6 | User、Role |
| v0.7 | Report |
| v0.8 | Slack連携 |
| v0.9 | Backlog連携 |
| v1.0 | 初回リリース(Docker、AWS、CI/CDを含む) |

---

## 学習方針

本API仕様書は「完成版仕様書」として維持する。

実装は以下の単位で進める。

- Milestone単位
- Issue単位
- Pull Request単位
