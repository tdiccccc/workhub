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
    <div class="flex items-center justify-between">
      <div>
        <h2 class="text-xl font-semibold tracking-wide text-slate-900">
          ダッシュボード
        </h2>
        <p class="mt-1 text-sm text-slate-500">
          Project の状況を確認できます。
        </p>
      </div>

      <NuxtLink
        to="/projects/create"
        class="bg-sky-800 px-4 py-2 text-sm font-semibold text-white hover:bg-sky-900"
      >
        + Project作成
      </NuxtLink>
    </div>

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