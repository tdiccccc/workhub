<script setup lang="ts">
import ProjectTable from "~/components/features/project/ProjectTable.vue";

definePageMeta({
  middleware: "auth",
});

const { fetchProjectList } = useProjects();
const { data, pending, error } = await fetchProjectList();
const projects = computed(() => data.value?.data ?? []);
const currentPage = ref(1);
const perPage = 10;

const totalPages = computed(() => {
  return Math.max(1, Math.ceil(projects.value.length / perPage));
});

const paginatedProjects = computed(() => {
  const start = (currentPage.value - 1) * perPage;
  return projects.value.slice(start, start + perPage);
});
</script>

<template>
  <div class="space-y-6">
    <UiAppPageHeader
      title="案件一覧"
      description="登録されている案件をテーブル形式で確認できます。"
    >
      <template #actions>
        <NuxtLink to="/projects/create">
          <UiAppButton variant="primary">
            新規作成
          </UiAppButton>
        </NuxtLink>
      </template>
    </UiAppPageHeader>

    <UiAppPanel title="案件一覧">
      <UiAppStatusMessage v-if="pending">
        読み込み中...
      </UiAppStatusMessage>

      <UiAppStatusMessage v-else-if="error" type="error">
        案件一覧の取得に失敗しました。
      </UiAppStatusMessage>

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

      <template v-else>
        <ProjectTable :projects="paginatedProjects" />
        <div class="mt-4 flex items-center justify-end gap-2">
          <UiAppButton
            :disabled="currentPage === 1"
            @click="currentPage--"
          >
            前へ
          </UiAppButton>

          <span class="text-sm text-slate-600">
            {{ currentPage }} / {{ totalPages }}
          </span>

          <UiAppButton
            :disabled="currentPage === totalPages"
            @click="currentPage++"
          >
            次へ
          </UiAppButton>
        </div>
      </template>
    </UiAppPanel>
  </div>
</template>
