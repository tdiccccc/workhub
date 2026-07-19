<script setup lang="ts">
const props = withDefaults(
  defineProps<{
    variant?: "primary" | "secondary" | "danger";
    type?: "button" | "submit";
    fullWidth?: boolean;
  }>(),
  {
    variant: "secondary",
    type: "button",
  },
);

const buttonClass = computed(() => {
  if (props.variant === "primary") {
    return "bg-sky-800 text-white hover:bg-sky-900";
  }

  if (props.variant === "danger") {
    return "border border-red-300 text-red-700 hover:bg-red-50";
  }

  return "border border-slate-300 text-slate-700 hover:bg-slate-100";
});
</script>

<template>
  <button
    :type="type"
    :class="[
      'px-4 py-2 text-sm font-semibold',
      buttonClass,
      fullWidth ? 'w-full' : '',
    ]"
  >
    <slot />
  </button>
</template>
