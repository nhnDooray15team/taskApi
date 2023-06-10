package com.nhnacademy.taskApi.dto.project.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
public class ProjectModifyRequest {
    @NotNull
    private String projectName;
    private String projectDescription;
    @NotNull
    private String statusName;
}
