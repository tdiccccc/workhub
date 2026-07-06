# フロントエンドアーキテクチャ

## 目的

本ドキュメントは、WorkHub のフロントエンド設計方針を定義する。

WorkHub のフロントエンドは、**Nuxt4 + Tailwind CSS** を用いて実装する。

今回はフロントエンドを作り込みすぎず、まずは **Nuxt4 を触りながら、Spring Boot API と接続して画面を動かすこと** を優先する。

---

## 技術スタック

| 分類       | 技術                |
| -------- | ----------------- |
| フレームワーク  | Nuxt4             |
| UI       | Vue 3             |
| スタイリング   | Tailwind CSS      |
| UIプリミティブ | Reka UI           |
| 状態管理     | Pinia             |
| バリデーション  | Zod               |
| API通信    | `$fetch` / ofetch |

---

## 基本方針

WorkHub では、以下の方針でフロントエンドを構成する。

* Nuxt4 の標準構成をベースにする
* Tailwind CSS でスタイリングする
* Reka UI は必要な箇所から利用する
* 状態管理は Pinia を採用する
* フォームバリデーションには Zod を利用する
* コンポーネント設計は **Feature-based + UI Components** を採用する
* 最初から作り込みすぎず、MVP完成を優先する

---

## アーキテクチャ方針

### Headless Component Architecture

本プロジェクトでは、ヘッドレスコンポーネントの考え方を採用する。

```text
UIコンポーネント = Reka UI（振る舞い） + Tailwind CSS（見た目）
```

### 方針

* Reka UI は Dialog, Dropdown, Select など、アクセシビリティやキーボード操作が必要なUIに利用する
* ボタン、入力欄、カードなどの基本UIは Tailwind CSS で自作する
* UIライブラリに依存しすぎず、まずは基本的なUIを自分で実装する

---

## ディレクトリ構成

```text
frontend/
└── app/
    ├── components/
    │   ├── ui/          # 業務知識を持たない共通UI
    │   ├── layout/      # Header, Sidebar など
    │   ├── common/      # Loading, EmptyState など
    │   └── features/    # 業務機能別コンポーネント
    │       ├── auth/
    │       ├── project/
    │       ├── task/
    │       └── worklog/
    │
    ├── composables/     # 再利用可能なロジック
    ├── layouts/         # Nuxt Layout
    ├── middleware/      # ルートミドルウェア
    ├── pages/           # 画面ルート
    ├── services/        # API通信
    ├── stores/          # Pinia Store
    ├── types/           # 型定義
    └── utils/           # 汎用関数
```

---

## components 方針

### ui

業務知識を持たない共通UIを配置する。

```text
components/ui/
├── Button.vue
├── Input.vue
├── Textarea.vue
├── Select.vue
├── Badge.vue
├── Card.vue
└── Dialog.vue
```

### layout

画面全体のレイアウト部品を配置する。

```text
components/layout/
├── AppHeader.vue
├── AppSidebar.vue
└── AppBreadcrumb.vue
```

### common

複数画面で使う共通機能コンポーネントを配置する。

```text
components/common/
├── LoadingSpinner.vue
├── EmptyState.vue
├── ErrorMessage.vue
└── ConfirmDialog.vue
```

### features

業務機能に紐づくコンポーネントを配置する。

```text
components/features/
├── auth/
│   └── LoginForm.vue
├── project/
│   ├── ProjectForm.vue
│   ├── ProjectTable.vue
│   └── ProjectCard.vue
├── task/
│   ├── TaskForm.vue
│   ├── TaskTable.vue
│   └── TaskStatusBadge.vue
└── worklog/
    ├── WorkLogForm.vue
    └── WorkLogTable.vue
```

---

## pages 方針

画面ルートは Nuxt4 のファイルベースルーティングに従う。

詳細は以下を参照する。

```text
docs/route/index.md
```

### MVP対象画面

* `/login`
* `/`
* `/projects`
* `/projects/create`
* `/projects/:projectId`
* `/projects/:projectId/edit`
* `/projects/:projectId/tasks`
* `/projects/:projectId/tasks/create`
* `/projects/:projectId/tasks/:taskId`
* `/projects/:projectId/tasks/:taskId/edit`
* `/work-logs`
* `/tasks/:taskId/work-logs`
* `/tasks/:taskId/work-logs/create`
* `/work-logs/:id/edit`
* `/settings/profile`

---

## layouts 方針

Nuxt4 の Layout を利用する。

```text
layouts/
├── default.vue
└── auth.vue
```

### default.vue

ログイン後の通常画面で使用する。

```text
default.vue
├── AppSidebar
├── AppHeader
└── Page
```

### auth.vue

ログイン画面で使用する。

```text
auth.vue
└── Page
```

---

## 状態管理

状態管理には Pinia を利用する。

```text
stores/
├── auth.ts
├── project.ts
├── task.ts
└── worklog.ts
```

### 方針

* 認証情報は `auth.ts` で管理する
* APIから取得した一覧・詳細データは必要に応じてStoreで管理する
* 最初からStoreに寄せすぎず、単純な画面では `useAsyncData` や `ref` も利用する
* 複数画面で共有する状態だけPiniaに置く

---

## API通信

API通信は `services/` 配下に集約する。

```text
services/
├── auth.ts
├── project.ts
├── task.ts
└── worklog.ts
```

### 方針

* Spring Boot APIとの通信は `$fetch` / ofetch を利用する
* APIのURLは `runtimeConfig` で管理する
* 画面コンポーネントから直接URLを書かない
* API仕様は `docs/api/index.md` に従う

---

## フォーム・バリデーション

フォームバリデーションには Zod を利用する。

```text
schemas/
├── auth.ts
├── project.ts
├── task.ts
└── worklog.ts
```

### 方針

* 入力値チェックは Zod Schema で定義する
* バックエンドでも必ずValidationを行う
* フロントエンドのValidationはUX向上のために行う
* Zodは最初から複雑に使いすぎず、必須・文字数・数値範囲を中心に使う

---

## 認証制御

認証が必要な画面は Middleware で制御する。

```text
middleware/
├── auth.ts
└── guest.ts
```

### 方針

* 未ログインユーザーは `/login` へリダイレクトする
* ログイン済みユーザーが `/login` にアクセスした場合は `/` へリダイレクトする
* MVPではロール制御は行わない
* ロール制御は User / Role 実装時に追加する

---

## 命名規則

### コンポーネント

```text
PascalCase
```

例:

```text
ProjectForm.vue
TaskTable.vue
WorkLogForm.vue
```

### composables

```text
useXxx
```

例:

```text
useAuth.ts
useProject.ts
useTask.ts
```

### stores

```text
useXxxStore
```

例:

```text
useAuthStore
useProjectStore
```

### services

```text
xxxService
```

例:

```text
projectService
taskService
```

---

## UI設計方針

* Tailwind CSS を採用する
* UIライブラリに依存しすぎない
* Reka UI は必要なUIから段階的に導入する
* コンポーネントは再利用を前提とする
* レスポンシブ対応を前提とする
* ダークモードは将来対応とする
* MVPではデザインの作り込みより、操作できることを優先する

---

## 開発方針

* まずは画面を動かすことを優先する
* 最初から完璧なコンポーネント設計を目指さない
* 共通化は2回以上同じ実装が出てから検討する
* フロントエンドに業務ロジックを持たせすぎない
* 業務ルールはバックエンド側に寄せる
* MVP完成後にUI改善・共通化・リファクタリングを行う
