export type Project = {
  id: number
  name: string
  amount: number
  description: string
  startedAt: string
  endedAt: string | null
  isActive: boolean
}