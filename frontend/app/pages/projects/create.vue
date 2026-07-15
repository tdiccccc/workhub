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
        :errors="errors"
      />
      <button type="submit">作成</button>
    </form>
  </div>
</template>
