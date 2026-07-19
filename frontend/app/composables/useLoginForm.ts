import { loginSchema } from "~/schemas/auth";

export const useLoginForm = () => {
    const email = ref("");
    const password = ref("");

    const errors = ref<Record<string, string>>({});
        const clearErrors = () => {
        errors.value = {};
    };

    const validate = () => {
        clearErrors();

        const result = loginSchema.safeParse(toPayload());

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

    const toPayload = () => {
        return {
            email: email.value,
            password: password.value,
        };
    };

    return {
        email,
        password,
        errors,
        validate,
        toPayload,
    };
}