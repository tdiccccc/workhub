<script setup lang="ts">
defineProps<{
  readonly?: boolean;
  errors: {
    name?: string;
    amount?: string;
    description?: string;
    startedAt?: string;
    endedAt?: string;
    isActive?: string;
  };
}>();

const name = defineModel<string>("name", { required: true });
const amount = defineModel<string>("amount", { required: true });
const description = defineModel<string>("description", { required: true });
const startedAt = defineModel<string>("startedAt", { required: true });
const endedAt = defineModel<string>("endedAt", { required: true });
const isActive = defineModel<boolean>("isActive", { required: true });
</script>

<template>
  <div class="grid gap-4">
    <UiAppInput
      id="name"
      v-model="name"
      label="プロジェクト名"
      type="text"
      :disabled="readonly"
      :error="errors.name"
    />

    <UiAppInput
      id="amount"
      v-model="amount"
      label="受注金額"
      type="number"
      :disabled="readonly"
      :error="errors.amount"
    />

    <UiAppTextarea
      id="description"
      v-model="description"
      label="概要"
      :disabled="readonly"
      :error="errors.description"
    />

    <div class="grid gap-4 md:grid-cols-2">
      <UiAppInput
        id="startedAt"
        v-model="startedAt"
        label="開始日時"
        type="datetime-local"
        :disabled="readonly"
        :error="errors.startedAt"
      />

      <UiAppInput
        id="endedAt"
        v-model="endedAt"
        label="終了日時"
        type="datetime-local"
        :disabled="readonly"
        :error="errors.endedAt"
      />
    </div>

    <UiAppCheckbox
      id="isActive"
      v-model="isActive"
      label="有効"
      :disabled="readonly"
      :error="errors.isActive"
    />
  </div>
</template>