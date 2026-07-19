<script setup lang="ts">
defineProps<{
  title: string;
  description?: string;
}>();
</script>

<template>
  <div class="border border-dashed border-slate-300 bg-slate-50 px-4 py-8 text-center">
    <p class="text-sm font-semibold text-slate-700">
      {{ title }}
    </p>

    <p v-if="description" class="mt-1 text-sm text-slate-500">
      {{ description }}
    </p>

    <div v-if="$slots.actions" class="mt-4">
      <slot name="actions" />
    </div>
  </div>
</template>