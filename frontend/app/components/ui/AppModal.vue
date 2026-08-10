<script setup lang="ts">
defineProps<{
  isOpen: boolean;
  title: string;
}>();

const emit = defineEmits<{
  close: [];
}>();
</script>

<template>
  <div
    v-if="isOpen"
    class="fixed inset-0 z-50 flex items-center justify-center bg-slate-900/50 px-4"
  >
    <section class="w-full max-w-lg bg-white p-6 shadow-xl">
      <div class="mb-4 flex items-center justify-between">
        <h2 class="text-lg font-semibold text-slate-900">
          {{ title }}
        </h2>

        <button
          type="button"
          class="text-sm text-slate-500 hover:text-slate-900"
          @click="emit('close')"
        >
          閉じる
        </button>
      </div>

      <slot />
    </section>
  </div>
</template>
