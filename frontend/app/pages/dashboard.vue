<script setup lang="ts">
type Project = {
  id: number;
  name: string;
  amount: number;
  description: string;
  startedAt: string;
  endedAt: string | null;
  isActive: boolean;
};

type ApiResponse<T> = {
  status: number;
  message: string;
  data: T;
};

const { data, pending, error } =
  await useFetch<ApiResponse<Project[]>>("/api/projects");
</script>

<template>
  <div>
    <h1>ダッシュボード</h1>
    <p>ログイン成功</p>
    <NuxtLink to="/projects/create"> プロジェクト作成 </NuxtLink>
    <section>
      <h2>プロジェクト一覧</h2>

      <p v-if="pending">読み込み中...</p>

      <p v-else-if="error">プロジェクト一覧の取得に失敗しました。</p>

      <p v-else-if="(data?.data ?? []).length === 0">
        プロジェクトはまだありません。
      </p>

      <ul v-else>
        <li v-for="project in data?.data ?? []" :key="project.id">
          <strong>{{ project.name }}</strong>
          <span> / {{ project.amount }}円</span>
          <p>{{ project.description }}</p>
        </li>
      </ul>
    </section>
  </div>
</template>
