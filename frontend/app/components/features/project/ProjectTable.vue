<script setup lang="ts">
import type { Project } from "~/types/project";

defineProps<{
  projects: Project[];
}>();
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
        <th class="border-b border-slate-300 px-3 py-2"></th>
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
          {{ project.amount }}
        </td>
        <td class="border-b border-slate-200 px-3 py-2">
          {{ project.startedAt }}
        </td>
        <td class="border-b border-slate-200 px-3 py-2">
          {{ project.endedAt ?? "-" }}
        </td>
        <td class="border-b border-slate-200 px-3 py-2">
          {{ project.isActive ? "有効" : "無効" }}
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