import type { ApiResponse } from '~/types/api'
import type { CurrentUser } from '~/types/auth'

export const login = async (email: string, password: string) => {
  return await $fetch<ApiResponse<null>>('/api/auth/login', {
    method: 'POST',
    body: {
      email,
      password,
    },
  })
}

export const logout = async () => {
  return await $fetch<ApiResponse<null>>('/api/auth/logout', {
    method: 'POST',
  })
}

export const fetchCurrentUser = async () => {
  return await $fetch<ApiResponse<CurrentUser>>('/api/auth/current-user')
}