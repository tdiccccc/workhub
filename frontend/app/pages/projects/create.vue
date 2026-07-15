<script setup lang="ts">
import { projectSchema } from "~/schemas/project";
import ProjectForm from "~/components/features/project/ProjectForm.vue";

definePageMeta({
  middleware: "auth",
});

const { createProject } = useProjects();
const { name, amount, description, startedAt, endedAt, isActive, toPayload } =
  useProjectForm();

const errorMessage = ref("");

const handleSubmit = async () => {
  errorMessage.value = "";

  const result = projectSchema.safeParse(toPayload());

  if (!result.success) {
    errorMessage.value =
      result.error.issues[0]?.message ?? "入力内容を確認してください";
    return;
  }

  try {
    await createProject(result.data);
    await navigateTo("/dashboard");
  } catch (error) {
    errorMessage.value = "プロジェクトの作成に失敗しました。";
  }
};
</script>

<template>
  <div>
    <h1>プロジェクト作成</h1>

    <p v-if="errorMessage">
      {{ errorMessage }}
    </p>

    <form @submit.prevent="handleSubmit">
      <ProjectForm
        v-model:name="name"
        v-model:amount="amount"
        v-model:description="description"
        v-model:startedAt="startedAt"
        v-model:endedAt="endedAt"
        v-model:isActive="isActive"
      />
      <button type="submit">作成</button>
    </form>
  </div>
</template>
