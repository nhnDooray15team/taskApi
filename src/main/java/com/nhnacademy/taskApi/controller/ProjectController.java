package com.nhnacademy.taskApi.controller;


import com.nhnacademy.taskApi.dto.project.request.ProjectCreateRequest;
import com.nhnacademy.taskApi.dto.project.request.ProjectGetResponse;
import com.nhnacademy.taskApi.dto.project.request.ProjectModifyRequest;
import com.nhnacademy.taskApi.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/projects")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProject(@RequestBody ProjectCreateRequest projectCreateRequest){
        projectService.createProject(projectCreateRequest);

    }

    @PatchMapping("/projects/{projects_no}/status")
    @ResponseStatus(HttpStatus.OK)
    public void modifyStatus(@PathVariable("projects_no") Long projectId ,
                             @RequestBody ProjectModifyRequest projectModifyRequest){
        projectService.modifyProject(projectId, projectModifyRequest);
    }

    @GetMapping("/projects")
    public List<ProjectGetResponse> getProjects(){
        return projectService.getAllProject();
    }

}
