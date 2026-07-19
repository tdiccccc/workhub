<script setup lang="ts">
withDefaults(
  defineProps<{
    id: string;
    label?: string;
    type?: string;
    modelValue: string | number | null;
    error?: string;
    disabled?: boolean;
    placeholder?: string;
  }>(),
  {
    type: "text",
  },
);

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

    <input
      :id="id"
      :type="type"
      :value="modelValue ?? ''"
      :disabled="disabled"
      :placeholder="placeholder"
      class="w-full border border-slate-300 bg-slate-50 px-3 py-2 text-sm outline-none focus:border-sky-700 focus:bg-white disabled:bg-slate-100 disabled:text-slate-500"
      @input="emit('update:modelValue', ($event.target as HTMLInputElement).value)"
    >

    <p v-if="error" class="mt-1 text-xs text-red-600">
      {{ error }}
    </p>
  </div>
</template>