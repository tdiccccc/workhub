# ローカル開発手順

## 前提

DevContainer で `/workspace` を開いていること。

## バックエンド起動

```bash
cd /workspace/backend
./gradlew bootRun
```

バックエンドAPIは以下で起動する。

```text
http://localhost:8200
```

## フロントエンド起動

```bash
cd /workspace/frontend
npm run dev
```

フロントエンドは以下で起動する。

```text
http://localhost:3000
```

## Docker Compose

DevContainer の土台になる Docker Compose は以下を利用する。

```text
docker/compose.yml
```

MySQL はホスト側から以下で接続する。

```text
localhost:3307
```

## よく使う確認

### Project一覧取得

```bash
curl -i http://localhost:8200/api/projects
```

認証が必要な場合は、ログインで取得した Cookie を利用する。

```bash
curl -i -b cookies.txt http://localhost:8200/api/projects
```

### ログイン

```bash
curl -i -c cookies.txt -X POST http://localhost:8200/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "admin@example.com",
    "password": "password"
  }'
```

### ログイン中ユーザー取得

```bash
curl -i -b cookies.txt http://localhost:8200/api/auth/current-user
```

### ログアウト

```bash
curl -i -b cookies.txt -X POST http://localhost:8200/api/auth/logout
```

## ビルド確認

### バックエンド

```bash
cd /workspace/backend
./gradlew build
```

### フロントエンド

```bash
cd /workspace/frontend
npm run build
```

## 補足

`cookies.txt` は curl でセッション確認を行うための一時ファイル。コミット対象にはしない。
