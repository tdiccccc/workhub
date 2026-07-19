<script setup lang="ts">
// defineModel:親の ref と子の input をつなぐ
const email = defineModel<string>("email", { required: true });
const password = defineModel<string>("password", { required: true });

defineProps<{
  errors: {
    email?: string;
    password?: string;
  };
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
  <form class="space-y-4" @submit.prevent="handleSubmit">
    <UiAppInput
      id="email"
      v-model="email"
      label="メールアドレス"
      type="email"
      :error="errors.email"
    />

    <UiAppInput
      id="password"
      v-model="password"
      label="パスワード"
      type="password"
      :error="errors.password"
    />

    <UiAppButton
      variant="primary"
      type="submit"
      full-width
    >
      ログイン
    </UiAppButton>
  </form>
</template>
