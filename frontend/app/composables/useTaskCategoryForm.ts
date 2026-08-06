import type { TaskCategory } from "~/types/taskCategory";
import { taskCategorySchema } from "~/schemas/taskCategory";

export const useTaskCategoryForm = () => {
  const name = ref("");
  const description = ref("");
  const color = ref("");
  const sortOrder = ref("");

  const errors = ref<Record<string, string>>({});

  const clearErrors = () => {
    errors.value = {};
  };

  const validate = () => {
    clearErrors();

    const result = taskCategorySchema.safeParse(toPayload());

    if (!result.success) {
      for (const issue of result.error.issues) {
        const fieldName = issue.path[0];

        if (typeof fieldName === "string") {
          errors.value[fieldName] = issue.message;
        }
      }

      return null;
    }

    return result.data;
  };

  const setForm = (taskCategory: TaskCategory) => {
    name.value = taskCategory.name;
    description.value = taskCategory.description;
    color.value = taskCategory.color;
    sortOrder.value = String(taskCategory.sortOrder);
  };

  const toPayload = () => {
    return {
      name: name.value,
      description: description.value,
      color: color.value,
      sortOrder: Number(sortOrder.value),
    };
  };

  return {
    name,
    description,
    color,
    sortOrder,
    errors,
    validate,
    setForm,
    toPayload,
  };
};
