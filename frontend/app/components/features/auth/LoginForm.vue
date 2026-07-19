<script setup lang="ts">
// defineModel:親の ref と子の input をつなぐ
const email = defineModel<string>("email", { required: true });
const password = defineModel<string>("password", { required: true });

const props = defineProps<{
  errors?: Record<string, string>;
}>();

// defineEmits:子から親へイベントを渡す
const emit = defineEmits<{
  submit: [];
}>();

// emit('submit'):フォーム送信されたことを親へ知らせる
const handleSubmit = () => {
  emit("submit");
};
</script>

<template>
  <form @submit.prevent="handleSubmit">
    <input
      v-model="email"
      type="email"
      name="email"
      placeholder="メールアドレスを入力してください"
    />
    <p v-if="errors?.email">{{ errors.email }}</p>

    <input
      v-model="password"
      type="password"
      name="password"
      placeholder="パスワードを入力してください"
    />
    <p v-if="errors?.password">{{ errors.password }}</p>

    <button type="submit">ログイン</button>
  </form>
</template>
