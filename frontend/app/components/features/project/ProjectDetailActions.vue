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
  <div v-if="!isEditing">
    <button type="button" @click="emit('edit')">編集</button>
  </div>

  <div v-else>
    <button type="button" @click="emit('update')">更新</button>
    <button type="button" @click="emit('delete')">削除</button>
    <button type="button" @click="emit('cancel')">キャンセル</button>
  </div>
</template>
