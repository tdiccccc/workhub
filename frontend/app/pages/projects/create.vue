<script setup lang="ts">
import ProjectForm from "~/components/features/project/ProjectForm.vue";

definePageMeta({
  middleware: "auth",
});

const { createProject } = useProjects();
const {
  name,
  amount,
  description,
  startedAt,
  endedAt,
  isActive,
  errors,
  validate,
} = useProjectForm();

const errorMessage = ref("");

const handleSubmit = async () => {
  const payload = validate();

  if (!payload) {
    return;
  }

  try {
    await createProject(payload);
    await navigateTo("/dashboard");
  } catch (error) {
    errorMessage.value = "プロジェクトの作成に失敗しました。";
  }
};
</script>

<template>
  <div class="mx-auto max-w-3xl space-y-6">
    <div>
      <NuxtLink to="/dashboard" class="text-sm text-sky-800 hover:text-sky-900">
        ダッシュボードへ戻る
      </NuxtLink>

      <h2 class="mt-3 text-xl font-semibold tracking-wide text-slate-900">
        Project作成
      </h2>
      <p class="mt-1 text-sm text-slate-500">
        新しいProjectを登録します。
      </p>
    </div>

    <section class="border border-slate-300 bg-white">
      <div class="border-b border-slate-300 px-4 py-3">
        <h3 class="text-base font-semibold tracking-wide text-slate-900">
          基本情報
        </h3>
      </div>

      <form class="space-y-6 p-4" @submit.prevent="handleSubmit">
        <p v-if="errorMessage" class="text-sm text-red-600">
          {{ errorMessage }}
        </p>

        <ProjectForm
          v-model:name="name"
          v-model:amount="amount"
          v-model:description="description"
          v-model:startedAt="startedAt"
          v-model:endedAt="endedAt"
          v-model:isActive="isActive"
          :errors="errors"
        />

        <div class="flex justify-end">
          <UiAppButton
            type="submit"
            variant="primary"
          >
            作成
          </UiAppButton>
        </div>
      </form>
    </section>
  </div>
</template>