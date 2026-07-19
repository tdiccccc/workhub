<script setup lang="ts">
const props = defineProps<{
  readonly?: boolean;
  errors?: Record<string, string>;
}>();

const name = defineModel<string>("name", { required: true });
const amount = defineModel<number>("amount", { required: true });
const description = defineModel<string>("description", { required: true });
const startedAt = defineModel<string>("startedAt", { required: true });
const endedAt = defineModel<string>("endedAt", { required: true });
const isActive = defineModel<boolean>("isActive", { required: true });
</script>

<template>
  <div class="grid gap-4">
    <div>
      <label for="name" class="mb-1 block text-sm font-medium text-slate-700">
        プロジェクト名
      </label>
      <input
        id="name"
        v-model="name"
        class="w-full border border-slate-300 bg-slate-50 px-3 py-2 text-sm outline-none focus:border-sky-700 focus:bg-white disabled:bg-slate-100"
        type="text"
        :readonly="readonly"
      />
      <p v-if="errors?.name" class="mt-1 text-xs text-red-600">
        {{ errors.name }}
      </p>
    </div>

    <div>
      <label for="amount" class="mb-1 block text-sm font-medium text-slate-700">
        受注金額
      </label>
      <input
        id="amount"
        v-model.number="amount"
        class="w-full border border-slate-300 bg-slate-50 px-3 py-2 text-sm outline-none focus:border-sky-700 focus:bg-white disabled:bg-slate-100"
        type="number"
        :readonly="readonly"
      />
      <p v-if="errors?.amount" class="mt-1 text-xs text-red-600">
        {{ errors.amount }}
      </p>
    </div>

    <div>
      <label
        for="description"
        class="mb-1 block text-sm font-medium text-slate-700"
      >
        概要
      </label>
      <textarea
        id="description"
        v-model="description"
        class="min-h-24 w-full border border-slate-300 bg-slate-50 px-3 py-2 text-sm outline-none focus:border-sky-700 focus:bg-white disabled:bg-slate-100"
        :readonly="readonly"
      />
      <p v-if="errors?.description" class="mt-1 text-xs text-red-600">
        {{ errors.description }}
      </p>
    </div>

    <div class="grid gap-4 md:grid-cols-2">
      <div>
        <label
          for="startedAt"
          class="mb-1 block text-sm font-medium text-slate-700"
        >
          開始日時
        </label>
        <input
          id="startedAt"
          v-model="startedAt"
          class="w-full border border-slate-300 bg-slate-50 px-3 py-2 text-sm outline-none focus:border-sky-700 focus:bg-white disabled:bg-slate-100"
          type="datetime-local"
          :readonly="readonly"
        />
        <p v-if="errors?.startedAt" class="mt-1 text-xs text-red-600">
          {{ errors.startedAt }}
        </p>
      </div>

      <div>
        <label
          for="endedAt"
          class="mb-1 block text-sm font-medium text-slate-700"
        >
          終了日時
        </label>
        <input
          id="endedAt"
          v-model="endedAt"
          class="w-full border border-slate-300 bg-slate-50 px-3 py-2 text-sm outline-none focus:border-sky-700 focus:bg-white disabled:bg-slate-100"
          type="datetime-local"
          :readonly="readonly"
        />
        <p v-if="errors?.endedAt" class="mt-1 text-xs text-red-600">
          {{ errors.endedAt }}
        </p>
      </div>
    </div>

    <label class="flex items-center gap-2 text-sm text-slate-700">
      <input
        v-model="isActive"
        class="h-4 w-4 border-slate-300 text-sky-800"
        type="checkbox"
        :disabled="readonly"
      />
      有効
    </label>
  </div>
</template>