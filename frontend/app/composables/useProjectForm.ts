import type { Project } from "~/types/project";

export const useProjectForm = () => {
  const name = ref("");
  const amount = ref(0);
  const description = ref("");
  const startedAt = ref("");
  const endedAt = ref("");
  const isActive = ref(false);

  const setForm = (project: Project) => {
    name.value = project.name;
    amount.value = project.amount;
    description.value = project.description;
    startedAt.value = project.startedAt;
    endedAt.value = project.endedAt ?? "";
    isActive.value = project.isActive;
  };

  const toPayload = () => {
    return {
      name: name.value,
      amount: amount.value,
      description: description.value,
      startedAt: startedAt.value,
      endedAt: endedAt.value || null,
      isActive: isActive.value,
    };
  };

  return {
    name,
    amount,
    description,
    startedAt,
    endedAt,
    isActive,
    setForm,
    toPayload,
  };
};