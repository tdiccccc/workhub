export default defineNuxtRouteMiddleware(async () => {
    if (import.meta.server) {
        console.log('認証ミドルウェア: サーバー側では処理をスキップ')
        return
    }

    console.log('認証ミドルウェア: クライアント側で認証状態を確認')
    const authStore = useAuthStore()

    if (authStore.isLoggedIn) {
        return
    }    
    
    // storeにuserがなければログイン画面にリダイレクト
    try {
        await authStore.fetchCurrentUser()
    } catch (error) {
        console.log('認証ミドルウェア: 未ログインのためログイン画面へ遷移')
        return navigateTo('/login')
    }
})