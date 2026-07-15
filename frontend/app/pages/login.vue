<script setup lang="ts">
import { loginSchema } from "~/schemas/auth";
import type { ApiResponse } from "~/types/api";
import type { CurrentUser } from "~/types/auth";

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

  //.  result.succes:成功したかどうか
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

    <form @submit.prevent="handleSubmit">
      <input
        v-model="email"
        type="email"
        name="email"
        placeholder="メールアドレスを入力してください"
      />

      <input
        v-model="password"
        type="password"
        name="password"
        placeholder="パスワードを入力してください"
      />

      <button type="submit">ログイン</button>
    </form>
  </div>
</template>
