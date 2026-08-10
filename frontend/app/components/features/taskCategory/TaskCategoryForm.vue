<script setup lang="ts">
defineProps<{
  taskCategoryErrors: {
    name?: string;
    description?: string;
    color?: string;
    sortOrder?: string;
  }
}>();

const taskCategoryName = defineModel<string>("taskCategoryName", { required: true });
const taskCategoryDescription = defineModel<string>("taskCategoryDescription", { required: true });
const taskCategoryColor = defineModel<string>("taskCategoryColor", { required: true });
const taskCategorySortOrder = defineModel<string>("taskCategorySortOrder", { required: true });
</script>

<template>
  <div class="grid gap-4">
    <UiAppInput
      id="taskCategoryName"
      v-model="taskCategoryName"
      label="カテゴリ名"
      :error="taskCategoryErrors.name"
    />

    <UiAppTextarea
      id="taskCategoryDescription"
      v-model="taskCategoryDescription"
      label="説明"
      :error="taskCategoryErrors.description"
    />

    <UiAppInput
      id="taskCategoryColor"
      v-model="taskCategoryColor"
      label="色"
      :error="taskCategoryErrors.color"
    />

    <UiAppInput
      id="taskCategorySortOrder"
      v-model="taskCategorySortOrder"
      label="並び順"
      :error="taskCategoryErrors.sortOrder"
    />
  </div>
</template>
