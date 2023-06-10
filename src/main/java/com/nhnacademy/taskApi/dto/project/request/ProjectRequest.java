package com.nhnacademy.taskApi.dto.project.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ProjectRequest {
    private String projectName;
    private String projectDescription;
}
