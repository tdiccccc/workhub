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

if (!projectId) {
  throw createError({
    statusCode: 404,
    statusMessage: "Project ID is required",
  });
}

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

  try {
    await updateProject(projectId, payload);
    await refresh();
    isEditing.value = false;
  } catch (error) {
    errorMessage.value = "プロジェクトの更新に失敗しました。";
  }
};

const handleDelete = async () => {
  const confirmed = window.confirm("このプロジェクトを削除しますか？");

  if (!confirmed) {
    return;
  }

  try {
    await deleteProject(projectId);
    await navigateTo("/dashboard");
  } catch (error) {
    errorMessage.value = "プロジェクトの削除に失敗しました。";
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
        title="Project詳細"
        description="Project の基本情報を確認・編集できます。"
      />
    </div>

    <UiAppStatusMessage v-if="errorMessage" type="error">
      {{ errorMessage }}
    </UiAppStatusMessage>

    <UiAppStatusMessage v-if="pending">
      読み込み中...
    </UiAppStatusMessage>

    <UiAppStatusMessage v-else-if="error" type="error">
      Project詳細の取得に失敗しました。
    </UiAppStatusMessage>

    <UiAppPanel v-else-if="data?.data" title="基本情報">
      <form class="space-y-6">
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
    </UiAppPanel>
  </div>
</template>
