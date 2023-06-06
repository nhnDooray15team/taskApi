package com.nhnacademy.taskApi.service.project;


import com.nhnacademy.taskApi.dto.project.request.ProjectRequest;


public interface ProjectService {
    void createProject(ProjectRequest projectRequest);
}
