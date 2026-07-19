<script setup lang="ts">
const props = defineProps<{
  isEditing: boolean;
}>();

const emit = defineEmits<{
  edit: [];
  update: [];
  delete: [];
  cancel: [];
}>();
</script>

<template>
  <div v-if="!isEditing" class="flex justify-end">
    <button
      type="button"
      class="border border-slate-300 px-4 py-2 text-sm font-semibold text-slate-700 hover:bg-slate-100"
      @click="emit('edit')"
    >
      編集
    </button>
  </div>

  <div v-else class="flex justify-end gap-2">
    <button
      type="button"
      class="bg-sky-800 px-4 py-2 text-sm font-semibold text-white hover:bg-sky-900"
      @click="emit('update')"
    >
      更新
    </button>

    <button
      type="button"
      class="border border-red-300 px-4 py-2 text-sm font-semibold text-red-700 hover:bg-red-50"
      @click="emit('delete')"
    >
      削除
    </button>

    <button
      type="button"
      class="border border-slate-300 px-4 py-2 text-sm font-semibold text-slate-700 hover:bg-slate-100"
      @click="emit('cancel')"
    >
      キャンセル
    </button>
  </div>
</template>