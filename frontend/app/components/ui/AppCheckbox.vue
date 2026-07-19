<script setup lang="ts">
defineProps<{
  id: string;
  label?: string;
  modelValue: boolean;
  error?: string;
  disabled?: boolean;
}>();

const emit = defineEmits<{
  "update:modelValue": [value: boolean];
}>();
</script>

<template>
  <div>
    <label
      :for="id"
      class="flex items-center gap-2 text-sm text-slate-700"
    >
      <input
        :id="id"
        type="checkbox"
        :checked="modelValue"
        :disabled="disabled"
        class="h-4 w-4 border-slate-300 text-sky-800 focus:ring-sky-700 disabled:cursor-not-allowed"
        @change="emit('update:modelValue', ($event.target as HTMLInputElement).checked)"
      >

      <span v-if="label">{{ label }}</span>
    </label>

    <p v-if="error" class="mt-1 text-xs text-red-600">
      {{ error }}
    </p>
  </div>
</template>