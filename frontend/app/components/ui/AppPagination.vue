<script setup lang="ts">
defineProps<{
  currentPage: number;
  totalPages: number;
}>();

const emit = defineEmits<{
  prev: [];
  next: [];
}>();
</script>

<template>
  <div class="mt-4 flex items-center justify-end gap-2">
    <UiAppButton
      :disabled="currentPage === 1"
      @click="emit('prev')"
    >
      前へ
    </UiAppButton>

    <span class="text-sm text-slate-600">
      {{ currentPage }} / {{ totalPages }}
    </span>

    <UiAppButton
      :disabled="currentPage === totalPages"
      @click="emit('next')"
    >
      次へ
    </UiAppButton>
  </div>
</template>