<script setup lang="ts">
import ProjectList from "~/components/features/project/ProjectList.vue";

definePageMeta({
  middleware: "auth",
});

const { fetchProjectList } = useProjects();
const { data, pending, error } = await fetchProjectList();
const projects = computed(() => data.value?.data ?? []);
</script>

<template>
  <div class="space-y-6">
    <UiAppPageHeader
      title="Project一覧"
      description="登録されているProjectを確認できます。"
    >
      <template #actions>
        <NuxtLink to="/projects/create">
          <UiAppButton variant="primary">
            新規作成
          </UiAppButton>
        </NuxtLink>
      </template>
    </UiAppPageHeader>

    <UiAppPanel title="Project一覧">
      <p v-if="pending" class="text-sm text-slate-500">
        読み込み中...
      </p>

      <p v-else-if="error" class="text-sm text-red-600">
        Project一覧の取得に失敗しました。
      </p>

      <UiAppEmptyState
        v-else-if="projects.length === 0"
        title="Project はまだありません"
        description="新しいProjectを作成すると、ここに一覧表示されます。"
      >
        <template #actions>
          <NuxtLink to="/projects/create">
            <UiAppButton variant="primary">
              新規作成
            </UiAppButton>
          </NuxtLink>
        </template>
      </UiAppEmptyState>

      <ProjectList v-else :projects="projects" />
    </UiAppPanel>
  </div>
</template>