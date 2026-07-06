# フロントエンド画面ルート定義

## 目的

既存の画面ルート定義は完成形としては良いが、今回の目的は**Spring Boot + Nuxt4 + Tailwind による学習プロジェクトを完遂すること**。

そのため、最初から全画面を実装するのではなく、MVPとして必要最小限の画面に絞る。

## 概要

Nuxt 4のファイルベースルーティングに基づく画面構成。

今回のフロントエンドは **Nuxt4 + Tailwind CSS** を前提とする。

---

## MVP実装対象画面

MVPでは以下のみ実装する。

```text
/
/login
/projects
/projects/create
/projects/:projectId
/projects/:projectId/edit
/projects/:projectId/tasks
/projects/:projectId/tasks/create
/projects/:projectId/tasks/:taskId
/projects/:projectId/tasks/:taskId/edit
/work-logs
/tasks/:taskId/work-logs
/tasks/:taskId/work-logs/create
/work-logs/:id/edit
/settings/profile
```

---

## ダッシュボード

`/` はログイン後の初期画面として扱う。

表示内容:

- 自分の担当タスク
- 今日の工数
- 最近更新したプロジェクト
- お知らせ(将来)

---

## Project配下にTaskを配置する

### 現状

```text
/tasks
/tasks/create
/tasks/:id
/tasks/:id/edit
```

### 修正案

```text
/projects/:projectId/tasks
/projects/:projectId/tasks/create
/projects/:projectId/tasks/:taskId
/projects/:projectId/tasks/:taskId/edit
```

### 理由

- Project と Task の親子関係が明確になる
- どの案件のタスクか分かりやすい
- API設計とも対応しやすい
- 業務システムらしい画面遷移になる

---

## WorkLogの扱い

作業ログは2種類の導線を持つ。

### 自分の作業ログ一覧

```text
/work-logs
```

用途:

- 自分が登録した作業ログを見る
- 日付で絞り込む
- 工数入力履歴を確認する

### タスクに対する作業ログ一覧

```text
/tasks/:taskId/work-logs
```

用途:

- タスクに紐づく工数一覧を見る
- 特定タスクの作業履歴を確認する

### タスクに対する作業ログ登録

```text
/tasks/:taskId/work-logs/create
```

用途:

- 特定タスクに対して工数を記録する

---

## MVPでは実装しない画面

以下は将来実装とする。

### タスクカテゴリ管理

```text
/projects/:projectId/categories
/projects/:projectId/categories/create
/projects/:projectId/categories/:id/edit
```

追加タイミング:

```text
v1.1 または v1.2
```

### ユーザー管理

```text
/users
/users/create
/users/:id
/users/:id/edit
```

追加タイミング:

```text
Spring Security / Role 管理フェーズ
```

### ロール管理

```text
/roles
/roles/create
/roles/:id/edit
```

追加タイミング:

```text
認可設計フェーズ
```

### レポート・集計

```text
/reports/projects
/reports/users
/reports/categories
```

追加タイミング:

```text
集計SQL / JPQL / QueryDSL 学習後
```

### Slack / Backlog 設定

```text
/settings/slack
/settings/backlog
```

追加タイミング:

```text
外部API連携フェーズ
```

---

## 修正後のルート一覧

### 共通

| パス | ファイルパス | 画面名 | 認証 |
|------|--------------|--------|------|
| `/` | `pages/index.vue` | ダッシュボード | 必須 |
| `/login` | `pages/login.vue` | ログイン | 不要 |

### プロジェクト管理

| パス | ファイルパス | 画面名 | 認証 |
|------|--------------|--------|------|
| `/projects` | `pages/projects/index.vue` | プロジェクト一覧 | 必須 |
| `/projects/create` | `pages/projects/create.vue` | プロジェクト作成 | 必須 |
| `/projects/:projectId` | `pages/projects/[projectId].vue` | プロジェクト詳細 | 必須 |
| `/projects/:projectId/edit` | `pages/projects/[projectId]/edit.vue` | プロジェクト編集 | 必須 |

### タスク管理

| パス | ファイルパス | 画面名 | 認証 |
|------|--------------|--------|------|
| `/projects/:projectId/tasks` | `pages/projects/[projectId]/tasks/index.vue` | プロジェクト別タスク一覧 | 必須 |
| `/projects/:projectId/tasks/create` | `pages/projects/[projectId]/tasks/create.vue` | タスク作成 | 必須 |
| `/projects/:projectId/tasks/:taskId` | `pages/projects/[projectId]/tasks/[taskId].vue` | タスク詳細 | 必須 |
| `/projects/:projectId/tasks/:taskId/edit` | `pages/projects/[projectId]/tasks/[taskId]/edit.vue` | タスク編集 | 必須 |

### 作業ログ管理

| パス | ファイルパス | 画面名 | 認証 |
|------|--------------|--------|------|
| `/work-logs` | `pages/work-logs/index.vue` | 自分の作業ログ一覧 | 必須 |
| `/tasks/:taskId/work-logs` | `pages/tasks/[taskId]/work-logs/index.vue` | タスク別作業ログ一覧 | 必須 |
| `/tasks/:taskId/work-logs/create` | `pages/tasks/[taskId]/work-logs/create.vue` | 作業ログ登録 | 必須 |
| `/work-logs/:id/edit` | `pages/work-logs/[id]/edit.vue` | 作業ログ編集 | 必須 |

### 設定

| パス | ファイルパス | 画面名 | 認証 |
|------|--------------|--------|------|
| `/settings/profile` | `pages/settings/profile.vue` | プロフィール設定 | 必須 |

---

## ナビゲーション構造

```text
/ ダッシュボード
├── /projects
│   ├── /projects/create
│   └── /projects/:projectId
│       ├── /projects/:projectId/edit
│       └── /projects/:projectId/tasks
│           ├── /projects/:projectId/tasks/create
│           └── /projects/:projectId/tasks/:taskId
│               ├── /projects/:projectId/tasks/:taskId/edit
│               └── /tasks/:taskId/work-logs
│                   └── /tasks/:taskId/work-logs/create
├── /work-logs
│   └── /work-logs/:id/edit
└── /settings/profile
```

---

## 権限要件

MVPでは権限を複雑にしない。

### MVP

| 権限 | 説明 |
|------|------|
| 未認証 | `/login` のみ |
| ログイン済みユーザー | 全画面アクセス可能 |

### 将来

| 権限 | 説明 |
|------|------|
| member | 一般メンバー |
| manager | プロジェクト管理者 |
| admin | システム管理者 |

---

## 画面遷移パターン

### プロジェクトからタスク確認

```text
/projects
→ /projects/:projectId
→ /projects/:projectId/tasks
→ /projects/:projectId/tasks/:taskId
```

### タスクに工数を登録

```text
/projects/:projectId/tasks/:taskId
→ /tasks/:taskId/work-logs
→ /tasks/:taskId/work-logs/create
```

### 自分の作業ログを確認

```text
/
→ /work-logs
```

---

## 実装順序

### Step 1

```text
/login
/
```

### Step 2

```text
/projects
/projects/create
/projects/:projectId
/projects/:projectId/edit
```

### Step 3

```text
/projects/:projectId/tasks
/projects/:projectId/tasks/create
/projects/:projectId/tasks/:taskId
/projects/:projectId/tasks/:taskId/edit
```

### Step 4

```text
/work-logs
/tasks/:taskId/work-logs
/tasks/:taskId/work-logs/create
/work-logs/:id/edit
```

### Step 5

```text
/settings/profile
```

---

## 将来追加予定

### v1.1

```text
カテゴリ管理
検索
ページネーション
ソート
```

### v1.2

```text
ユーザー管理
ロール管理
```

### v1.3

```text
レポート
集計
```

### v2.0

```text
Slack連携
Backlog連携
通知
```

### v3.0

```text
スケジュール管理
カレンダー
ガントチャート
メンバー管理
```

---

## レイアウト構成

Nuxt4ではLayout、Component、Pageを分離して画面を構成する。

```text
layouts/
├── default.vue
└── auth.vue

components/
├── common/
├── layout/
├── project/
├── task/
└── work-log/
```

通常画面は `default.vue` を使用する。

```text
default.vue
↓
Sidebar
↓
Header
↓
Page
```

ログイン画面のみ `auth.vue` を使用する。

```text
auth.vue
↓
Page
```

---

## コンポーネント設計

画面固有の処理はPageに寄せすぎず、再利用できるUIはComponentとして分離する。

```text
components/common/
```

ボタン、入力欄、モーダル、テーブルなどの汎用UIを配置する。

```text
components/layout/
```

Sidebar、Header、Breadcrumbなどレイアウトに関わる部品を配置する。

```text
components/project/
components/task/
components/work-log/
```

Project、Task、WorkLogに閉じたフォーム、一覧、詳細表示用の部品を配置する。

---

## 状態管理

状態管理にはPiniaを採用する。

認証状態、ログインユーザー、画面間で共有するProject、Task、WorkLogの状態は `stores/` に分離する。

```text
stores/
├── auth.ts
├── project.ts
├── task.ts
└── workLog.ts
```

将来的なJWT、ログイン状態、プロフィール情報もPiniaで管理する。

---

## Middleware

認証が必要な画面はNuxt middlewareで制御する。

```text
middleware/
├── auth.ts
└── guest.ts
```

`auth.ts` はログイン済みユーザーのみアクセス可能な画面で使用する。

`guest.ts` は未認証ユーザー向けの `/login` で使用し、ログイン済みの場合はダッシュボードへ遷移する。

---

## ディレクトリ構成

Nuxt4の基本構成は以下を前提とする。

```text
pages/
components/
layouts/
composables/
middleware/
plugins/
stores/
types/
services/
utils/
```

`services/` にはAPI通信処理を配置し、PageやComponentから直接HTTPクライアントを呼びすぎないようにする。

`types/` にはAPIレスポンスやフォーム入力値の型定義を配置する。

`composables/` には画面横断で再利用するロジックを配置する。

---

## UI設計方針

- Tailwind CSS を採用する
- UIライブラリには依存しない
- コンポーネントは再利用を前提とする
- shadcn-vue は採用しない
- 基本的なUIを自作し、Tailwindへの理解を深める
- レスポンシブ対応を前提とする
- ダークモードは将来対応とする

---

## 方針

今回のルート定義は、

```text
完成形を全部作る設計
```

ではなく、

```text
完遂できる最小構成から育てる設計
```

とする。

まずはMVPを完成させることを最優先にする。

MVP完成後は、GitHub Milestoneに沿って段階的に画面を追加していく。

完成形を一度に実装するのではなく、リリース可能な単位で継続的に開発する。
