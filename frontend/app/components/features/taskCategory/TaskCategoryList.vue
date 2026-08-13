<script setup lang="ts">
import type { TaskCategory } from "~/types/taskCategory";

defineProps<{
  taskCategories: TaskCategory[];
}>();

const emit = defineEmits<{
  edit: [id: number];
  delete: [id: number];
}>();
</script>

<template>
  <ul class="divide-y divide-slate-200">
    <li
      v-for="taskCategory in taskCategories"
      :key="taskCategory.id"
      class="flex items-center justify-between gap-4 py-3"
    >
      <div class="flex min-w-0 items-center gap-3">
        <span
          class="h-3 w-3 shrink-0 rounded-full"
          :style="{ backgroundColor: taskCategory.color }"
        />

        <div class="min-w-0">
          <div class="flex items-center gap-2">
            <p class="truncate text-sm font-medium text-slate-900">
              {{ taskCategory.name }}
            </p>

            <span class="text-xs text-slate-500"> #{{ taskCategory.sortOrder }} </span>
          </div>

          <p class="mt-1 truncate text-xs text-slate-500">
            {{ taskCategory.description || "説明なし" }}
          </p>
        </div>
      </div>

      <div class="flex shrink-0 items-center gap-2">
        <UiAppButton @click="emit('edit', taskCategory.id)"> 編集 </UiAppButton>

        <UiAppButton variant="danger" @click="emit('delete', taskCategory.id)"> 削除 </UiAppButton>
      </div>
    </li>
  </ul>
</template>
