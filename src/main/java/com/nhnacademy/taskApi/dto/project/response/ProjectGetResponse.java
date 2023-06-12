package com.nhnacademy.taskApi.dto.project.response;


import com.nhnacademy.taskApi.domain.Authority;
import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.domain.ProjectStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ProjectGetResponse {
    private Long projectId;
    private String projectName;
    private Authority.Role role;
    private ProjectStatus.StatusName statusName;
    @QueryProjection
    public ProjectGetResponse(Long projectId, String projectName, Authority.Role role, ProjectStatus.StatusName statusName){
        this.projectId = projectId;
        this.projectName = projectName;
        this.role = role;
        this.statusName = statusName;
    }
}
