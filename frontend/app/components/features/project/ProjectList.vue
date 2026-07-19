<script setup lang="ts">
import type { Project } from "~/types/project";

const props = defineProps<{
  projects: Project[];
}>();
</script>

<template>
  <ul>
    <li v-for="project in projects" :key="project.id">
      <NuxtLink :to="`/projects/${project.id}`">
        <strong>{{ project.name }}</strong>
      </NuxtLink>
      <span> / {{ project.amount }}円</span>
      <p>{{ project.description }}</p>
    </li>
  </ul>
</template>
