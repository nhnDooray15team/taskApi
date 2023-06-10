package com.nhnacademy.taskApi.controller;

import com.nhnacademy.taskApi.dto.project.response.ProjectGetResponse;
import com.nhnacademy.taskApi.dto.project.request.ProjectModifyRequest;
import com.nhnacademy.taskApi.dto.project.request.ProjectRequest;
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
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/projects/user/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertProject(@RequestBody @Valid ProjectRequest projectCreateRequest,
                              BindingResult bindingResult,
                              @PathVariable("userId") String userId){
        if(bindingResult.hasErrors()){
            throw new ValidationException();
        }
        projectService.createProject(projectCreateRequest, userId);

    }

    @PatchMapping("/projects/{projectId}/status")
    @ResponseStatus(HttpStatus.OK)
    public void modifyStatus(@PathVariable("projectId") Long projectId ,
                             @RequestBody @Valid ProjectModifyRequest projectModifyRequest,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException();
        }
        projectService.modifyProject(projectId, projectModifyRequest);
    }

    @GetMapping("/projects/users/{userId}")
    public Page<ProjectGetResponse> getProjects(@PageableDefault(size = 5)Pageable pageable,
                                                @PathVariable("userId") String userId){
        return projectService.getAllProject(pageable, userId);
    }

}
