<script setup lang="ts">
import type { TaskCategory } from "~/types/taskCategory";

defineProps<{
  taskCategories: TaskCategory[];
  editingTaskCategoryId: number | null;
}>();

const emit = defineEmits<{
  edit: [id: number];
  cancelEdit: [];
  delete: [id: number];
}>();
</script>

<template>
  <ul class="space-y-2">
    <li v-for="taskCategory in taskCategories" :key="taskCategory.id">
      <span
        class="inline-block h-3 w-3 rounded-full"
        :style="{ backgroundColor: taskCategory.color }"
      />
      <template v-if="editingTaskCategoryId === taskCategory.id">
        <span>{{ taskCategory.name }} を編集中</span>

        <UiAppButton @click="emit('cancelEdit')"> キャンセル </UiAppButton>
      </template>

      <template v-else>
        {{ taskCategory.name }}

        <UiAppButton @click="emit('edit', taskCategory.id)"> 編集 </UiAppButton>

        <UiAppButton variant="danger" @click="emit('delete', taskCategory.id)"> 削除 </UiAppButton>
      </template>
    </li>
  </ul>
</template>
