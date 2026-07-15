import type { ApiResponse } from '~/types/api'
import type { Project } from '~/types/project'
import type { ProjectForm } from '~/schemas/project'

export const fetchProjects = async () => {
  return await $fetch<ApiResponse<Project[]>>('/api/projects')
}

export const fetchProject = async (projectId: string | string[]) => {
  return await $fetch<ApiResponse<Project>>(`/api/projects/${projectId}`)
}

export const createProject = async (form: ProjectForm) => {
  return await $fetch<ApiResponse<Project>>('/api/projects', {
    method: 'POST',
    body: form,
  })
}

export const updateProject = async (
  projectId: string | string[],
  form: ProjectForm,
) => {
  return await $fetch<ApiResponse<Project>>(`/api/projects/${projectId}`, {
    method: 'PUT',
    body: form,
  })
}

export const deleteProject = async (projectId: string | string[]) => {
  return await $fetch<ApiResponse<null>>(`/api/projects/${projectId}`, {
    method: 'DELETE',
  })
}