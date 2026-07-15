import { z } from 'zod'

export const projectSchema = z.object({
    name: z
        .string()
        .min(1, 'プロジェクト名は必須です')
        .max(50, "50文字以内で入力してください"),

    amount: z
        .number('受注金額は数字で入力してください')
        .min(0, '受注金額は0以上で入力してください'),

    description: z
        .string()
        .min(1, '案件概要は必須です')
        .max(250, "250文字以内で入力してください"),

    startedAt: z
        .string()
        .min(1, '開始日時は必須です'),
    endedAt: z
        .string()
        .nullable(),    

    isActive: z
        .boolean(),
})

export type ProjectForm = z.infer<typeof projectSchema>