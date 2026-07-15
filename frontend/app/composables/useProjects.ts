import {
  fetchProject,
  fetchProjects,
  updateProject,
} from "~/services/project";
import type { ProjectForm } from "~/schemas/project";

export const useProjects = () => {
  const fetchProjectList = () => {
    return useAsyncData("projects", fetchProjects);
  };

  // project-${projectId}:詳細は ID ごとに違うデータのため
  const fetchProjectDetail = (projectId: string | string[]) => {
    return useAsyncData(`project-${projectId}`, () => fetchProject(projectId));
  };

  const updateProjectDetail = async (
    projectId: string | string[],
    form: ProjectForm,
  ) => {
    return await updateProject(projectId, form);
  }

  return {
    fetchProjectList,
    fetchProjectDetail,
    updateProjectDetail,
  };
};