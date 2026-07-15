import { z } from 'zod'

export const loginSchema = z.object({
  // pipe()はまず string + min を検証、その後 email形式を検証
  email: z
    .string()
    .min(1, 'メールアドレスは必須です')
    .pipe(z.email('メールアドレスの形式で入力してください')),

  password: z
    .string()
    .min(1, 'パスワードは必須です'),
})

export type LoginForm = z.infer<typeof loginSchema>