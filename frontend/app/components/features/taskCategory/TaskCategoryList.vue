<script setup lang="ts">
import type { TaskCategory } from "~/types/taskCategory"

defineProps<{
  taskCategories: TaskCategory[];
}>();
</script>

<template>
  <ul class="space-y-2">
    <li
      v-for="taskCategory in taskCategories"
      :key="taskCategory.id"
      class="border-b border-slate-200 py-2"
    >
      {{ taskCategory.name }}
    </li>
  </ul>
</template>
