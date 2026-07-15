import type { CurrentUser } from '~/types/auth'

import {
  fetchCurrentUser as fetchCurrentUserApi,
  login as loginApi,
  logout as logoutApi,
} from '~/services/auth'

export const useAuthStore = defineStore('auth', () => {
    // ログイン中のユーザー
    const user = ref<CurrentUser | null>(null)

    // userがあるかどうかから作る派生状態
    const isLoggedIn = computed(() => user.value !== null)

    /**
     * ログインしているユーザーを取得
     */
    const fetchCurrentUser = async () => {
        const response = await fetchCurrentUserApi()

        user.value = response.data
    }

    /**
     * ログイン
     * @param email 
     * @param password 
     */
    const login = async (email: string, password: string) => {
        await loginApi(email, password)

        await fetchCurrentUser()
    }

    /**
     * ログアウト
     */
    const logout = async () => {
        await logoutApi()

        // ユーザーをnull
        user.value = null

        // ログイン画面にリダイレクト
        await navigateTo('/login')
    }

    return {
        user,
        isLoggedIn,
        fetchCurrentUser,
        login,
        logout,
    }
})