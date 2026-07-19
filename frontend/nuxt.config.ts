export default defineNuxtConfig({
  modules: ['@pinia/nuxt', '@nuxtjs/tailwindcss'],
  compatibilityDate: '2025-07-15',
  devtools: { enabled: true },
  tailwindcss: {
    cssPath: '~/assets/css/tailwind.css',
  },
  routeRules: {
    '/api/**': {
      proxy: 'http://localhost:8200/api/**'
    }
  }
})