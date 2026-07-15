import {
    createProject,
    fetchProject,
    fetchProjects,
    updateProject,
    deleteProject,
} from "~/services/project";

import type { ProjectForm } from "~/schemas/project";

export const useProjects = () => {
    const createProjectDetail = async (form: ProjectForm) => {
        return await createProject(form);
    }
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

    const deleteProjectDetail = async (projectId: string | string[]) => {
        return await deleteProject(projectId);
    }

    return {
        createProjectDetail,
        fetchProjectList,
        fetchProjectDetail,
        updateProjectDetail,
        deleteProjectDetail,
    };
};