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
  <div>
    <h1>ログイン</h1>

    <p v-if="errorMessage">
      {{ errorMessage }}
    </p>

    <LoginForm
      v-model:email="email"
      v-model:password="password"
      :errors="errors"
      @submit="handleSubmit"
    />
  </div>
</template>
