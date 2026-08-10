export type TaskCategory = {
  id: number;
  projectId: number;
  name: string;
  description: string;
  color: string;
  sortOrder: number;
  createdAt: string;
  updatedAt: string | null;
};
