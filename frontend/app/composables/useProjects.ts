import { fetchProjects } from "~/services/project";

export const useProjects = () => {
  const fetchProjectList = () => {
    return useAsyncData("projects", fetchProjects);
  };

  return {
    fetchProjectList,
  };
};