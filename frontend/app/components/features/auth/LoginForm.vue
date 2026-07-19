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
  <form class="space-y-4" @submit.prevent="handleSubmit">
    <div>
      <label class="mb-1 block text-sm font-medium text-slate-700">
        メールアドレス
      </label>
      <input
        v-model="email"
        class="w-full border border-slate-300 bg-slate-50 px-3 py-2 text-sm outline-none focus:border-sky-700 focus:bg-white"
        type="email"
        name="email"
        placeholder="admin@example.com"
      />
      <p v-if="errors?.email" class="mt-1 text-xs text-red-600">
        {{ errors.email }}
      </p>
    </div>

    <div>
      <label class="mb-1 block text-sm font-medium text-slate-700">
        パスワード
      </label>
      <input
        v-model="password"
        class="w-full border border-slate-300 bg-slate-50 px-3 py-2 text-sm outline-none focus:border-sky-700 focus:bg-white"
        type="password"
        name="password"
        placeholder="password"
      />
      <p v-if="errors?.password" class="mt-1 text-xs text-red-600">
        {{ errors.password }}
      </p>
    </div>

    <button
      class="w-full bg-sky-800 px-4 py-2 text-sm font-semibold text-white hover:bg-sky-900"
      type="submit"
    >
      ログイン
    </button>
  </form>
</template>
