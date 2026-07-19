import type { Project } from "~/types/project";
import { projectSchema } from "~/schemas/project";

export const useProjectForm = () => {
    const name = ref("");
    const amount = ref("");
    const description = ref("");
    const startedAt = ref("");
    const endedAt = ref("");
    const isActive = ref(false);

    const errors = ref<Record<string, string>>({});

    const clearErrors = () => {
        errors.value = {};
    };

    const validate = () => {
        clearErrors();

        const result = projectSchema.safeParse(toPayload());

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

    const setForm = (project: Project) => {
        name.value = project.name;
        amount.value = String(project.amount);
        description.value = project.description;
        startedAt.value = project.startedAt;
        endedAt.value = project.endedAt ?? "";
        isActive.value = project.isActive;
    };

    const toPayload = () => {
        return {
            name: name.value,
            amount: Number(amount.value),
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
        errors,
        validate,
        setForm,
        toPayload,
    };
};