<script setup lang="ts">
import LoginForm from "~/components/features/auth/LoginForm.vue";
import { loginSchema } from "~/schemas/auth";

// definePageMeta:そのページ専用の設定を書くためのNuxtの関数
definePageMeta({
  layout: "auth",
});

const email = ref("");
const password = ref("");
const errorMessage = ref("");

const authStore = useAuthStore();

const handleSubmit = async () => {
  errorMessage.value = "";

  // バリデーションに成功しても失敗しても例外を投げず、結果を返す
  const result = loginSchema.safeParse({
    email: email.value,
    password: password.value,
  });

  // result.success: 成功したかどうか
  if (!result.success) {
    errorMessage.value =
      result.error.issues[0]?.message ?? "入力内容を確認してください";
    return;
  }

  await authStore.login(result.data.email, result.data.password);

  await navigateTo("/dashboard");
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
      @submit="handleSubmit"
    />
  </div>
</template>
