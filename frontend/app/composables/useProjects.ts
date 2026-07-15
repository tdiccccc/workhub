import { fetchProject, fetchProjects } from "~/services/project";

export const useProjects = () => {
  const fetchProjectList = () => {
    return useAsyncData("projects", fetchProjects);
  };

  // project-${projectId}:詳細は ID ごとに違うデータのため
  const fetchProjectDetail = (projectId: string | string[]) => {
    return useAsyncData(`project-${projectId}`, () => fetchProject(projectId));
  };

  return {
    fetchProjectList,
    fetchProjectDetail,
  };
};