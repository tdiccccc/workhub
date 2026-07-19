<script setup lang="ts">
const props = withDefaults(
  defineProps<{
    type?: "info" | "error" | "success";
  }>(),
  {
    type: "info",
  },
);

const messageClass = computed(() => {
  if (props.type === "error") {
    return "text-red-600";
  }

  if (props.type === "success") {
    return "text-emerald-700";
  }

  return "text-slate-500";
});
</script>

<template>
  <p :class="['text-sm', messageClass]">
    <slot />
  </p>
</template>