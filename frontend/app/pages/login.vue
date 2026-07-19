<script setup lang="ts">
import LoginForm from "~/components/features/auth/LoginForm.vue";

// definePageMeta:そのページ専用の設定を書くためのNuxtの関数
definePageMeta({
  layout: "auth",
});

const { email, password, errors, validate } = useLoginForm();

const errorMessage = ref("");

const authStore = useAuthStore();

const handleSubmit = async () => {
  const payload = validate();

  if (!payload) {
    return;
  }

  try {
    await authStore.login(payload.email, payload.password);
    await navigateTo("/dashboard");
  } catch (error) {
    errorMessage.value = "ログインに失敗しました。";
  }
};
</script>

<template>
  <div class="flex min-h-screen items-center justify-center px-6">
    <section class="w-full max-w-sm border border-slate-300 bg-white p-8 shadow-sm">
      <h1 class="mb-2 text-2xl font-semibold tracking-wide text-slate-900">
        WorkHub
      </h1>

      <p class="mb-6 text-sm text-slate-500">
        案件・工数・進捗をひとつに。
      </p>

      <p v-if="errorMessage" class="mb-4 text-sm text-red-600">
        {{ errorMessage }}
      </p>
      
      <LoginForm
        v-model:email="email"
        v-model:password="password"
        :errors="errors"
        @submit="handleSubmit"
      />
    </section>
  </div>
</template>
