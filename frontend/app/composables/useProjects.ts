import {
    createProject as createProjectApi,
    deleteProject as deleteProjectApi,
    fetchProject,
    fetchProjects,
    updateProject as updateProjectApi,
} from "~/services/project";

import type { ProjectForm } from "~/schemas/project";

export const useProjects = () => {
    const createProject = async (form: ProjectForm) => {
        return await createProjectApi(form);
    };

    const fetchProjectList = () => {
        return useAsyncData("projects", fetchProjects);
    };

    // project-${projectId}:詳細は ID ごとに違うデータのため
    const fetchProjectDetail = (projectId: string | string[]) => {
        return useAsyncData(`project-${projectId}`, () => fetchProject(projectId));
    };

    const updateProject = async (
        projectId: string | string[],
        form: ProjectForm,
    ) => {
        return await updateProjectApi(projectId, form);
    };

    const deleteProject = async (projectId: string | string[]) => {
        return await deleteProjectApi(projectId);
    };

    return {
        createProject,
        fetchProjectList,
        fetchProjectDetail,
        updateProject,
        deleteProject,
    };
};