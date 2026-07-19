<script setup lang="ts">
defineProps<{
  title?: string;
}>();
</script>

<template>
  <section class="border border-slate-300 bg-white">
    <div
      v-if="title || $slots.header"
      class="border-b border-slate-300 px-4 py-3"
    >
      <slot name="header">
        <h3 class="text-base font-semibold tracking-wide text-slate-900">
          {{ title }}
        </h3>
      </slot>
    </div>

    <div class="p-4">
      <slot />
    </div>
  </section>
</template>