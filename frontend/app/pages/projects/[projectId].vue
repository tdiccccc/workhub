<script setup lang="ts">
import ProjectForm from "~/components/features/project/ProjectForm.vue";
import ProjectDetailActions from "~/components/features/project/ProjectDetailActions.vue";

definePageMeta({
  middleware: "auth",
});

const { fetchProjectDetail, updateProject, deleteProject } = useProjects();
const isEditing = ref(false);
const errorMessage = ref("");

const {
  name,
  amount,
  description,
  startedAt,
  endedAt,
  isActive,
  errors,
  validate,
  setForm,
} = useProjectForm();

const route = useRoute();
const projectId = route.params.projectId;

const { data, pending, error, refresh } = await fetchProjectDetail(projectId);

if (data.value?.data) {
  setForm(data.value.data);
}

const startEditing = () => {
  isEditing.value = true;
};

const cancelEditing = () => {
  if (data.value?.data) {
    setForm(data.value.data);
  }

  isEditing.value = false;
};

const handleUpdate = async () => {
  const payload = validate();

  if (!payload) {
    return;
  }

  await updateProject(projectId, payload);

  await refresh();
  isEditing.value = false;
};

const handleDelete = async () => {
  const confirmed = window.confirm("このプロジェクトを削除しますか？");

  if (!confirmed) {
    return;
  }

  await deleteProject(projectId);

  await navigateTo("/dashboard");
};
</script>

<template>
  <div>
    <NuxtLink to="/dashboard"> ダッシュボードへ戻る </NuxtLink>

    <h1>プロジェクト詳細</h1>

    <p v-if="errorMessage">
      {{ errorMessage }}
    </p>

    <p v-if="pending">読み込み中...</p>

    <p v-else-if="error">プロジェクト詳細の取得に失敗しました。</p>

    <form v-else-if="data?.data">
      <ProjectForm
        v-model:name="name"
        v-model:amount="amount"
        v-model:description="description"
        v-model:startedAt="startedAt"
        v-model:endedAt="endedAt"
        v-model:isActive="isActive"
        :readonly="!isEditing"
        :errors="errors"
      />

      <ProjectDetailActions
        :is-editing="isEditing"
        @edit="startEditing"
        @update="handleUpdate"
        @delete="handleDelete"
        @cancel="cancelEditing"
      />
    </form>
  </div>
</template>
