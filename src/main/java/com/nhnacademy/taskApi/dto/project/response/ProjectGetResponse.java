package com.nhnacademy.taskApi.dto.project.response;


import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectGetResponse {

    private Long projectId;
    private String projectName;
    private String role;
    private String statusName;

    @QueryProjection
    public ProjectGetResponse(Long projectId, String projectName, String role, String statusName) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.role = role;
        this.statusName = statusName;
    }
}
