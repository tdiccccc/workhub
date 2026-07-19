<script setup lang="ts">
const authStore = useAuthStore();

const handleLogout = async () => {
  await authStore.logout();
};
</script>

<template>
  <header class="flex h-14 items-center border-b border-slate-300 bg-white px-6">
    <h1 class="text-lg font-semibold tracking-wide text-slate-900">
      ダッシュボード
    </h1>

    <div class="ml-auto flex items-center gap-4">
      <span class="text-sm text-slate-600">
        {{ authStore.user?.email }}
      </span>

      <button
        type="button"
        class="border border-slate-300 px-3 py-1.5 text-sm text-slate-700 hover:bg-slate-100"
        @click="handleLogout"
      >
        ログアウト
      </button>
    </div>
  </header>
</template>