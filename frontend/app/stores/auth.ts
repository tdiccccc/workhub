type CurrentUser = {
    email: string
}

type ApiResponse<T> = {
    status: number
    message: string
    data: T
}

export const useAuthStore = defineStore('auth', () => {
    // ログイン中のユーザー
    const user = ref<CurrentUser | null>(null)

    // userがあるかどうかから作る派生状態
    const isLoggedIn = computed(() => user.value !== null)

    /**
     * ログインしているユーザーを取得
     */
    const fetchCurrentUser = async () => {
        const response = await $fetch<ApiResponse<CurrentUser>>('/api/auth/current-user')

        user.value = response.data
    }

    /**
     * ログイン
     * @param email 
     * @param password 
     */
    const login = async (email: string, password: string) => {
        await $fetch<ApiResponse<null>>('/api/auth/login', {
        method: 'POST',
        body: {
            email,
            password,
        },
        })

        await fetchCurrentUser()
    }

    /**
     * ログアウト
     */
    const logout = async () => {
        await $fetch<ApiResponse<null>>('/api/auth/logout', {
        method: 'POST',
        })

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