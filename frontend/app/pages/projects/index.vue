<script setup lang="ts">
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
        <table class="w-full border-collapse text-sm">
          <thead class="bg-slate-100 text-left text-slate-700">
            <tr>
              <th class="border-b border-slate-300 px-3 py-2">Project名</th>
              <th class="border-b border-slate-300 px-3 py-2">受注金額</th>
              <th class="border-b border-slate-300 px-3 py-2">開始日時</th>
              <th class="border-b border-slate-300 px-3 py-2">終了日時</th>
              <th class="border-b border-slate-300 px-3 py-2">状態</th>
              <th class="border-b border-slate-300 px-3 py-2"></th>
            </tr>
          </thead>

          <tbody>
            <tr
              v-for="project in paginatedProjects"
              :key="project.id"
              class="hover:bg-slate-50"
            >
              <td class="border-b border-slate-200 px-3 py-2">
                {{ project.name }}
              </td>
              <td class="border-b border-slate-200 px-3 py-2">
                {{ project.amount }}
              </td>
              <td class="border-b border-slate-200 px-3 py-2">
                {{ project.startedAt }}
              </td>
              <td class="border-b border-slate-200 px-3 py-2">
                {{ project.endedAt }}
              </td>
              <td class="border-b border-slate-200 px-3 py-2">
                {{ project.isActive ? "有効" : "無効" }}
              </td>
              <td class="border-b border-slate-200 px-3 py-2 text-right">
                <NuxtLink
                  :to="`/projects/${project.id}`"
                  class="text-sky-800 hover:text-sky-900"
                >
                  詳細
                </NuxtLink>
              </td>
            </tr>
          </tbody>
        </table>

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
