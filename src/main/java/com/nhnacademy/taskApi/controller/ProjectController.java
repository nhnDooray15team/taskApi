package com.nhnacademy.taskApi.controller;

import com.nhnacademy.taskApi.dto.project.request.ProjectModifyRequest;
import com.nhnacademy.taskApi.dto.project.request.ProjectRequest;
import com.nhnacademy.taskApi.dto.project.response.ProjectGetResponse;
import com.nhnacademy.taskApi.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertProject(@RequestBody @Valid ProjectRequest projectCreateRequest,
                              @PathVariable("userId") String userId){

        projectService.createProject(projectCreateRequest, userId);

    }

    @PatchMapping("/{projectId}/status")
    @ResponseStatus(HttpStatus.OK)
    public void modifyStatus(@PathVariable("projectId") Long projectId ,
                             @RequestBody @Valid ProjectModifyRequest projectModifyRequest){

        projectService.modifyProject(projectId, projectModifyRequest);
    }

    @GetMapping("/users/{userId}")
    public Page<ProjectGetResponse> getProjects(@PageableDefault(size = 5)Pageable pageable,
                                                @PathVariable("userId") String userId){
        return projectService.getAllProject(pageable, userId);
    }

}
