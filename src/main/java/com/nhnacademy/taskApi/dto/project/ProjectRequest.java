package com.nhnacademy.taskApi.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectRequest {
    private Long projectId;
    private String projectName;
    private String projectDescription;
    private Long statusId;
}
