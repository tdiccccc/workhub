<script setup lang="ts">
import { projectSchema } from "~/schemas/project";
import ProjectForm from "~/components/features/project/ProjectForm.vue";

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
  setForm,
  toPayload,
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
  errorMessage.value = "";

  const result = projectSchema.safeParse(toPayload());

  if (!result.success) {
    errorMessage.value =
      result.error.issues[0]?.message ?? "入力内容を確認してください";
    return;
  }

  await updateProject(projectId, result.data);

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
      />

      <div v-if="!isEditing">
        <button type="button" @click="startEditing">編集</button>
      </div>

      <div v-else>
        <button type="button" @click="handleUpdate">更新</button>

        <button type="button" @click="handleDelete">削除</button>

        <button type="button" @click="cancelEditing">キャンセル</button>
      </div>
    </form>
  </div>
</template>
