package com.nhnacademy.taskApi.dto.project.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectGetResponse {
    private Long projectId;
    private String projectName;
    private Integer statusId;
    private String role;
}
