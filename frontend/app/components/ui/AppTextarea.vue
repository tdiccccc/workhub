<script setup lang="ts">
defineProps<{
  id: string;
  label?: string;
  modelValue: string | null;
  error?: string;
  disabled?: boolean;
  placeholder?: string;
}>();

const emit = defineEmits<{
  "update:modelValue": [value: string];
}>();
</script>

<template>
  <div>
    <label
      v-if="label"
      :for="id"
      class="mb-1 block text-sm font-medium text-slate-700"
    >
      {{ label }}
    </label>

    <textarea
      :id="id"
      :value="modelValue ?? ''"
      :disabled="disabled"
      :placeholder="placeholder"
      class="min-h-24 w-full border border-slate-300 bg-slate-50 px-3 py-2 text-sm outline-none focus:border-sky-700 focus:bg-white disabled:bg-slate-100"
      @input="emit('update:modelValue', ($event.target as HTMLInputElement).value)"
    />

    <p v-if="error" class="mt-1 text-xs text-red-600">
      {{ error }}
    </p>
  </div>
</template>