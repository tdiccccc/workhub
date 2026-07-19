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

    <p v-if="errorMessage" class="text-sm text-red-600">
      {{ errorMessage }}
    </p>

    <p v-if="pending" class="text-sm text-slate-500">
      読み込み中...
    </p>

    <p v-else-if="error" class="text-sm text-red-600">
      Project詳細の取得に失敗しました。
    </p>

    <form v-else-if="data?.data" class="border border-slate-300 bg-white">
      <div class="border-b border-slate-300 px-4 py-3">
        <h3 class="text-base font-semibold tracking-wide text-slate-900">
          基本情報
        </h3>
      </div>

      <div class="space-y-6 p-4">
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
      </div>
    </form>
  </div>
</template>
