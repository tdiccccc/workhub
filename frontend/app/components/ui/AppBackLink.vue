<script setup lang="ts">
defineProps<{
  to: string;
}>();
</script>

<template>
  <NuxtLink
    :to="to"
    class="text-sm text-sky-800 hover:text-sky-900"
  >
    <slot />
  </NuxtLink>
</template>