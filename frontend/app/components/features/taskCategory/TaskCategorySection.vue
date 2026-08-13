<script setup lang="ts">
import TaskCategoryList from "~/components/features/taskCategory/TaskCategoryList.vue";
import TaskCategoryForm from "~/components/features/taskCategory/TaskCategoryForm.vue";

const props = defineProps<{
  projectId: string | string[];
}>();

const { fetchTaskCategoryList, createTaskCategory, deleteTaskCategory, updateTaskCategory } =
  useTaskCategories();

const {
  data: taskCategoryData,
  pending: taskCategoryPending,
  error: taskCategoryError,
} = await fetchTaskCategoryList(props.projectId);

const taskCategories = computed(() => taskCategoryData.value?.data ?? []);

const {
  name: taskCategoryName,
  description: taskCategoryDescription,
  color: taskCategoryColor,
  sortOrder: taskCategorySortOrder,
  errors: taskCategoryErrors,
  validate: validateTaskCategory,
  resetForm: resetTaskCategoryForm,
} = useTaskCategoryForm();

const taskCategoryCreatePending = ref(false);
const taskCategoryCreateError = ref("");
const isTaskCategoryCreateModalOpen = ref(false);
const editingTaskCategoryId = ref<number | null>(null);
const taskCategoryUpdatePending = ref(false);
const taskCategoryUpdateError = ref("");

const {
  name: editingTaskCategoryName,
  description: editingTaskCategoryDescription,
  color: editingTaskCategoryColor,
  sortOrder: editingTaskCategorySortOrder,
  errors: editingTaskCategoryErrors,
  validate: validateEditingTaskCategory,
  setForm: setEditingTaskCategoryForm,
  resetForm: resetEditingTaskCategoryForm,
} = useTaskCategoryForm();

const startEditTaskCategory = (taskCategoryId: number) => {
  const taskCategory = taskCategories.value.find(
    (taskCategory) => taskCategory.id === taskCategoryId,
  );

  if (!taskCategory) {
    return;
  }

  editingTaskCategoryId.value = taskCategoryId;
  setEditingTaskCategoryForm(taskCategory);
};

const cancelEditTaskCategory = () => {
  editingTaskCategoryId.value = null;
  resetEditingTaskCategoryForm();
};

const handleCreateTaskCategory = async () => {
  const payload = validateTaskCategory();

  if (!payload) {
    return;
  }

  try {
    taskCategoryCreatePending.value = true;
    taskCategoryCreateError.value = "";

    await createTaskCategory(props.projectId, payload);
    await refreshNuxtData(`task-categories-${props.projectId}`);
    resetTaskCategoryForm();
  } catch {
    taskCategoryCreateError.value = "タスクカテゴリの作成に失敗しました。";
  } finally {
    taskCategoryCreatePending.value = false;
  }
};

const handleDeleteTaskCategory = async (taskCategoryId: number) => {
  const confirmed = window.confirm("このタスクカテゴリを削除しますか？");

  if (!confirmed) {
    return;
  }

  try {
    await deleteTaskCategory(props.projectId, String(taskCategoryId));
    await refreshNuxtData(`task-categories-${props.projectId}`);
  } catch {
    taskCategoryCreateError.value = "タスクカテゴリの削除に失敗しました。";
  }
};

const isTaskCategoryEditModalOpen = computed(() => {
  return editingTaskCategoryId.value !== null;
});

const handleUpdateTaskCategory = async () => {
  if (editingTaskCategoryId.value === null) {
    return;
  }

  const payload = validateEditingTaskCategory();

  if (!payload) {
    return;
  }

  try {
    taskCategoryUpdatePending.value = true;
    taskCategoryUpdateError.value = "";

    await updateTaskCategory(props.projectId, String(editingTaskCategoryId.value), payload);

    await refreshNuxtData(`task-categories-${props.projectId}`);
    cancelEditTaskCategory();
  } catch {
    taskCategoryUpdateError.value = "タスクカテゴリの更新に失敗しました。";
  } finally {
    taskCategoryUpdatePending.value = false;
  }
};
</script>
<template>
  <UiAppPanel title="タスクカテゴリ">
    <UiAppStatusMessage v-if="taskCategoryPending"> 読み込み中... </UiAppStatusMessage>

    <UiAppStatusMessage v-else-if="taskCategoryError" type="error">
      タスクカテゴリの取得に失敗しました。
    </UiAppStatusMessage>

    <UiAppEmptyState
      v-else-if="taskCategories.length === 0"
      title="タスクカテゴリはまだありません"
      description="このProjectで使う作業分類を追加できます。"
    />

    <TaskCategoryList
      v-else
      :task-categories="taskCategories"
      :editing-task-category-id="editingTaskCategoryId"
      @edit="startEditTaskCategory"
      @cancel-edit="cancelEditTaskCategory"
      @delete="handleDeleteTaskCategory"
    />

    <UiAppModal
      :is-open="isTaskCategoryEditModalOpen"
      title="タスクカテゴリ編集"
      @close="cancelEditTaskCategory"
    >
      <form class="space-y-4" @submit.prevent="handleUpdateTaskCategory">
        <TaskCategoryForm
          v-model:task-category-name="editingTaskCategoryName"
          v-model:task-category-description="editingTaskCategoryDescription"
          v-model:task-category-color="editingTaskCategoryColor"
          v-model:task-category-sort-order="editingTaskCategorySortOrder"
          :task-category-errors="editingTaskCategoryErrors"
        />

        <UiAppStatusMessage v-if="taskCategoryUpdateError" type="error">
          {{ taskCategoryUpdateError }}
        </UiAppStatusMessage>

        <div class="flex justify-end gap-2">
          <UiAppButton type="button" @click="cancelEditTaskCategory"> キャンセル </UiAppButton>

          <UiAppButton type="submit" variant="primary" :disabled="taskCategoryUpdatePending">
            更新
          </UiAppButton>
        </div>
      </form>
    </UiAppModal>
  </UiAppPanel>

  <UiAppStatusMessage v-if="taskCategoryCreateError" type="error">
    {{ taskCategoryCreateError }}
  </UiAppStatusMessage>

  <UiAppButton variant="primary" @click="isTaskCategoryCreateModalOpen = true">
    カテゴリ作成
  </UiAppButton>

  <UiAppModal
    :is-open="isTaskCategoryCreateModalOpen"
    title="タスクカテゴリ作成"
    @close="isTaskCategoryCreateModalOpen = false"
  >
    <form class="space-y-4" @submit.prevent="handleCreateTaskCategory">
      <TaskCategoryForm
        v-model:task-category-name="taskCategoryName"
        v-model:task-category-description="taskCategoryDescription"
        v-model:task-category-color="taskCategoryColor"
        v-model:task-category-sort-order="taskCategorySortOrder"
        :task-category-errors="taskCategoryErrors"
      />

      <UiAppButton type="submit" variant="primary" :disabled="taskCategoryCreatePending">
        作成
      </UiAppButton>
    </form>
  </UiAppModal>
</template>
