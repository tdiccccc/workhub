<script setup lang="ts">
defineProps<{
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
    <UiAppButton @click="emit('edit')">
      編集
    </UiAppButton>
  </div>

  <div v-else class="flex justify-end gap-2">
    <UiAppButton variant="primary" @click="emit('update')">      
      更新
    </UiAppButton>

    <UiAppButton variant="danger" @click="emit('delete')">
      削除
    </UiAppButton>

    <UiAppButton @click="emit('cancel')">
      キャンセル
    </UiAppButton>
  </div>
</template>