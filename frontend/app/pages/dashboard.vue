<script setup lang="ts">
import ProjectList from "~/components/features/project/ProjectList.vue";

definePageMeta({
  middleware: "auth",
});

const authStore = useAuthStore();
const { fetchProjectList } = useProjects();

const { data, pending, error } = await fetchProjectList();
const projects = computed(() => data.value?.data ?? []);

const handleLogout = async () => {
  await authStore.logout();
};
</script>

<template>
  <div>
    <h1>ダッシュボード</h1>
    <p>ログイン成功</p>
    <button type="button" @click="handleLogout">ログアウト</button>
    <NuxtLink to="/projects/create"> プロジェクト作成 </NuxtLink>
    <section>
      <h2>プロジェクト一覧</h2>

      <p v-if="pending">読み込み中...</p>

      <p v-else-if="error">プロジェクト一覧の取得に失敗しました。</p>
      <p v-else-if="projects.length === 0">プロジェクトはまだありません。</p>
      <ProjectList v-else :projects="projects" />
    </section>
  </div>
</template>
