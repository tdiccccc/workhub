import { z } from "zod";

export const taskCategorySchema = z.object({
  name: z.string().min(1, "タスクカテゴリー名は必須です").max(50, "50文字以内で入力してください"),
  description: z.string().max(250, "250文字以内で入力してください"),
  color: z.string(),
  sortOrder: z.coerce
    .number("並び順は数字で入力してください")
    .min(0, "並び順は0以上で入力してください"),
});

export type TaskCategoryForm = z.infer<typeof taskCategorySchema>;
