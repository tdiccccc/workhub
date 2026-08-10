<script setup lang="ts">
import type { Project } from "~/types/project";

defineProps<{
  projects: Project[];
}>();

const formatAmount = (amount: number) => {
  return `${amount.toLocaleString()}円`;
};

const formatDateTime = (dateTime: string | null) => {
  if (!dateTime) {
    return "-";
  }

  return new Date(dateTime).toLocaleString("ja-JP", {
    year: "numeric",
    month: "2-digit",
    day: "2-digit",
    hour: "2-digit",
    minute: "2-digit",
  });
};
</script>

<template>
  <table class="w-full border-collapse text-sm">
    <thead class="bg-slate-100 text-left text-slate-700">
      <tr>
        <th class="border-b border-slate-300 px-3 py-2">Project名</th>
        <th class="border-b border-slate-300 px-3 py-2">受注金額</th>
        <th class="border-b border-slate-300 px-3 py-2">開始日時</th>
        <th class="border-b border-slate-300 px-3 py-2">終了日時</th>
        <th class="border-b border-slate-300 px-3 py-2">状態</th>
        <th class="border-b border-slate-300 px-3 py-2">
          <span class="sr-only">操作</span>
        </th>
      </tr>
    </thead>

    <tbody>
      <tr
        v-for="project in projects"
        :key="project.id"
        class="hover:bg-slate-50"
      >
        <td class="border-b border-slate-200 px-3 py-2">
          {{ project.name }}
        </td>
        <td class="border-b border-slate-200 px-3 py-2">
          {{ formatAmount(project.amount )}}
        </td>
        <td class="border-b border-slate-200 px-3 py-2">
          {{ formatDateTime(project.startedAt) }}
        </td>
        <td class="border-b border-slate-200 px-3 py-2">
          {{ formatDateTime(project.endedAt) }}
        </td>
        <td class="border-b border-slate-200 px-3 py-2">
            <span
                :class="[
                    'inline-flex rounded-full px-2 py-1 text-xs font-semibold',
                    project.isActive
                    ? 'bg-green-100 text-green-700'
                    : 'bg-slate-100 text-slate-600',
                ]"
                >
                {{ project.isActive ? "有効" : "無効" }}
            </span>
        </td>
        <td class="border-b border-slate-200 px-3 py-2 text-right">
          <NuxtLink
            :to="`/projects/${project.id}`"
            class="text-sky-800 hover:text-sky-900"
          >
            詳細
          </NuxtLink>
        </td>
      </tr>
    </tbody>
  </table>
</template>
