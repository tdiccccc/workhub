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

      <UiAppPageHeader
        title="Project作成"
        description="新しいProjectを登録します。"
      />
    </div>
    <UiAppPanel title="基本情報">
      <form class="space-y-6" @submit.prevent="handleSubmit">
        <UiAppStatusMessage v-if="errorMessage" type="error">
          {{ errorMessage }}
        </UiAppStatusMessage>

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
    </UiAppPanel>
  </div>
</template>