export default defineNuxtRouteMiddleware(async () => {
    const authStore = useAuthStore()

    if (authStore.isLoggedIn) {
        return
    }    
    
    // storeにuserがなければログイン画面にリダイレクト
    try {
        await authStore.fetchCurrentUser()
    } catch {
        return navigateTo('/login')
    }
})