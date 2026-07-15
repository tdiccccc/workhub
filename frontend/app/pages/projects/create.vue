<script setup lang="ts">
import { projectSchema } from "~/schemas/project";

definePageMeta({
  middleware: "auth",
});

const name = ref("");
const amount = ref(0);
const description = ref("");
const startedAt = ref("");
const endedAt = ref("");
const isActive = ref(false);

const errorMessage = ref("");

const handleSubmit = async () => {
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

  try {
    await $fetch("/api/projects", {
      method: "POST",
      body: result.data,
    });
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
      <div>
        <label for="name">プロジェクト名</label>
        <input id="name" v-model="name" type="text" />
      </div>

      <div>
        <label for="amount">受注金額</label>
        <input id="amount" v-model.number="amount" type="number" />
      </div>

      <div>
        <label for="description">概要</label>
        <textarea id="description" v-model="description" />
      </div>

      <div>
        <label for="startedAt">開始日時</label>
        <input id="startedAt" v-model="startedAt" type="datetime-local" />
      </div>

      <div>
        <label for="endedAt">終了日時</label>
        <input id="endedAt" v-model="endedAt" type="datetime-local" />
      </div>

      <div>
        <label>
          <input v-model="isActive" type="checkbox" />
          有効
        </label>
      </div>

      <button type="submit">作成</button>
    </form>
  </div>
</template>
