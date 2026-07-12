<script setup lang="ts">
const isEditing = ref(false);

const name = ref("");
const amount = ref(0);
const description = ref("");
const startedAt = ref("");
const endedAt = ref("");
const isActive = ref(false);

type Project = {
  id: number;
  name: string;
  amount: number;
  description: string;
  startedAt: string;
  endedAt: string | null;
  isActive: boolean;
};

type ApiResponse<T> = {
  status: number;
  message: string;
  data: T;
};

const route = useRoute();
const projectId = route.params.projectId;

const { data, pending, error, refresh } = await useFetch<ApiResponse<Project>>(
  `/api/projects/${projectId}`,
);

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
  await $fetch(`/api/projects/${projectId}`, {
    method: "PUT",
    body: {
      name: name.value,
      amount: amount.value,
      description: description.value,
      startedAt: startedAt.value,
      endedAt: endedAt.value || null,
      isActive: isActive.value,
    },
  });

  await refresh();
  isEditing.value = false;
};

const handleDelete = async () => {
  const confirmed = window.confirm("このプロジェクトを削除しますか？");

  if (!confirmed) {
    return;
  }

  await $fetch(`/api/projects/${projectId}`, {
    method: "DELETE",
  });

  await navigateTo("/dashboard");
};
</script>

<template>
  <div>
    <NuxtLink to="/dashboard"> ダッシュボードへ戻る </NuxtLink>

    <h1>プロジェクト詳細</h1>

    <p v-if="pending">読み込み中...</p>

    <p v-else-if="error">プロジェクト詳細の取得に失敗しました。</p>

    <form v-else-if="data?.data">
      <div>
        <label for="name">プロジェクト名</label>
        <input id="name" v-model="name" type="text" :readonly="!isEditing" />
      </div>

      <div>
        <label for="amount">受注金額</label>
        <input
          id="amount"
          v-model.number="amount"
          type="number"
          :readonly="!isEditing"
        />
      </div>

      <div>
        <label for="description">概要</label>
        <textarea
          id="description"
          v-model="description"
          :readonly="!isEditing"
        />
      </div>

      <div>
        <label for="startedAt">開始日時</label>
        <input
          id="startedAt"
          v-model="startedAt"
          type="datetime-local"
          :readonly="!isEditing"
        />
      </div>

      <div>
        <label for="endedAt">終了日時</label>
        <input
          id="endedAt"
          v-model="endedAt"
          type="datetime-local"
          :readonly="!isEditing"
        />
      </div>

      <div>
        <label>
          <input v-model="isActive" type="checkbox" :disabled="!isEditing" />
          有効
        </label>
      </div>

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
