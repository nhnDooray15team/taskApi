package com.nhnacademy.taskApi.service.project;


import com.nhnacademy.taskApi.dto.project.request.ProjectCreateRequest;
import com.nhnacademy.taskApi.dto.project.request.ProjectGetResponse;
import com.nhnacademy.taskApi.dto.project.request.ProjectModifyRequest;

import java.util.List;


public interface ProjectService {
    void createProject(ProjectCreateRequest projectCreateRequest);

    void modifyProject(Long projectId, ProjectModifyRequest projectModifyRequest);


    List<ProjectGetResponse> getAllProject();
}
