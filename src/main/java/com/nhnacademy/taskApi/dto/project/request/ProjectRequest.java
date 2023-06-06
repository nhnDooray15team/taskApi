package com.nhnacademy.taskApi.dto.project.request;


import com.nhnacademy.taskApi.domain.ProjectStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRequest {
    private int projectId;
    private String projectName;
    private String projectDescription;
    private ProjectStatus statusId;
}
