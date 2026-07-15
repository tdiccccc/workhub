<script setup lang="ts">
import { projectSchema } from "~/schemas/project";
import type { Project } from "~/types/project";
import { deleteProject, updateProject } from "~/services/project";
import ProjectForm from "~/components/features/project/ProjectForm.vue";

definePageMeta({
  middleware: "auth",
});

const { fetchProjectDetail } = useProjects();
const isEditing = ref(false);
const errorMessage = ref("");

const name = ref("");
const amount = ref(0);
const description = ref("");
const startedAt = ref("");
const endedAt = ref("");
const isActive = ref(false);

const route = useRoute();
const projectId = route.params.projectId;

const { data, pending, error, refresh } = await fetchProjectDetail(projectId);

const setForm = (project: Project) => {
  name.value = project.name;
  amount.value = project.amount;
  description.value = project.description;
  startedAt.value = project.startedAt;
  endedAt.value = project.endedAt ?? "";
  isActive.value = project.isActive;
};

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

  const result = projectSchema.safeParse({
    name: name.value,
    amount: amount.value,
    description: description.value,
    startedAt: startedAt.value,
    endedAt: endedAt.value || null,
    isActive: isActive.value,
  });

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
