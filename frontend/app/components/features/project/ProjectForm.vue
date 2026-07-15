<script setup lang="ts">
const props = defineProps<{
  readonly?: boolean;
}>();

const name = defineModel<string>("name", { required: true });
const amount = defineModel<number>("amount", { required: true });
const description = defineModel<string>("description", { required: true });
const startedAt = defineModel<string>("startedAt", { required: true });
const endedAt = defineModel<string>("endedAt", { required: true });
const isActive = defineModel<boolean>("isActive", { required: true });
</script>
<template>
  <div>
    <label for="name">プロジェクト名</label>
    <input id="name" v-model="name" type="text" :readonly="readonly" />
  </div>

  <div>
    <label for="amount">受注金額</label>
    <input
      id="amount"
      v-model.number="amount"
      type="number"
      :readonly="readonly"
    />
  </div>

  <div>
    <label for="description">概要</label>
    <textarea id="description" v-model="description" :readonly="readonly" />
  </div>

  <div>
    <label for="startedAt">開始日時</label>
    <input
      id="startedAt"
      v-model="startedAt"
      type="datetime-local"
      :readonly="readonly"
    />
  </div>

  <div>
    <label for="endedAt">終了日時</label>
    <input
      id="endedAt"
      v-model="endedAt"
      type="datetime-local"
      :readonly="readonly"
    />
  </div>

  <div>
    <label>
      <input v-model="isActive" type="checkbox" :disabled="readonly" />
      有効
    </label>
  </div>
</template>
