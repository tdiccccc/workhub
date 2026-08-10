import {
  createTaskCategory as createTaskCategoryApi,
  deleteTaskCategory as deleteTaskCategoryApi,
  fetchTaskCategory,
  fetchTaskCategories,
  updateTaskCategory as updateTaskCategoryApi,
} from "~/services/taskCategory";

import type { TaskCategoryForm } from "~/schemas/taskCategory";

export const useTaskCategories = () => {
  const createTaskCategory = async (projectId: string | string[], form: TaskCategoryForm) => {
    return await createTaskCategoryApi(projectId, form);
  };

  const fetchTaskCategoryList = (projectId: string | string[]) => {
    return useAsyncData(`task-categories-${projectId}`, () => fetchTaskCategories(projectId));
  };

  const fetchTaskCategoryDetail = (
    projectId: string | string[],
    taskCategoryId: string | string[],
  ) => {
    return useAsyncData(`task-category-${projectId}-${taskCategoryId}`, () =>
      fetchTaskCategory(projectId, taskCategoryId),
    );
  };

  const updateTaskCategory = async (
    projectId: string | string[],
    taskCategoryId: string | string[],
    form: TaskCategoryForm,
  ) => {
    return await updateTaskCategoryApi(projectId, taskCategoryId, form);
  };

  const deleteTaskCategory = async (
    projectId: string | string[],
    taskCategoryId: string | string[],
  ) => {
    return await deleteTaskCategoryApi(projectId, taskCategoryId);
  };

  return {
    createTaskCategory,
    fetchTaskCategoryList,
    fetchTaskCategoryDetail,
    deleteTaskCategory,
    updateTaskCategory,
  };
};
