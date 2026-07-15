<script setup lang="ts">
const email = ref("");
const password = ref("");

const authStore = useAuthStore();

const handleSubmit = async () => {
  await authStore.login(email.value, password.value);

  await navigateTo("/dashboard");
};
</script>

<template>
  <div>
    <h1>ログイン</h1>

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
