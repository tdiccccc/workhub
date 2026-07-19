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

    <section class="border border-slate-300 bg-white">
      <div class="border-b border-slate-300 px-4 py-3">
        <h3 class="text-base font-semibold tracking-wide text-slate-900">
          Project一覧
        </h3>
      </div>

      <div class="p-4">
        <p v-if="pending" class="text-sm text-slate-500">
          読み込み中...
        </p>

        <p v-else-if="error" class="text-sm text-red-600">
          Project一覧の取得に失敗しました。
        </p>

        <p v-else-if="projects.length === 0" class="text-sm text-slate-500">
          Project はまだありません。
        </p>

        <ProjectList v-else :projects="projects" />
      </div>
    </section>
  </div>
</template>