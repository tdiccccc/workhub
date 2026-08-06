import type { ApiResponse } from '~/types/api'
import type { TaskCategory } from '~/types/taskCategory'
import type { TaskCategoryForm } from '~/schemas/taskCategory'

export const fetchTaskCategories = async (projectId: string | string[]) => {
  return await $fetch<ApiResponse<TaskCategory[]>>(`/api/projects/${projectId}/task-categories`)
}

export const fetchTaskCategory = async (
  projectId: string | string[],
  taskCategoryId: string | string[],
) => {
  return await $fetch<ApiResponse<TaskCategory>>(`/api/projects/${projectId}/task-categories/${taskCategoryId}`)
}

export const createTaskCategory = async (
  projectId: string | string[],
  form: TaskCategoryForm,
) => {
  return await $fetch<ApiResponse<TaskCategory>>(`/api/projects/${projectId}/task-categories`, {
    method: 'POST',
    body: form,
  })
}

export const updateTaskCategory = async (
  projectId: string | string[],
  taskCategoryId: string | string[],
  form: TaskCategoryForm,
) => {
  return await $fetch<ApiResponse<TaskCategory>>(`/api/projects/${projectId}/task-categories/${taskCategoryId}`, {
    method: 'PUT',
    body: form,
  })
}

export const deleteTaskCategory = async (
  projectId: string | string[],
  taskCategoryId: string | string[],
) => {
  return await $fetch<ApiResponse<null>>(`/api/projects/${projectId}/task-categories/${taskCategoryId}`, {
    method: 'DELETE',
  })
}
