package com.nhnacademy.taskApi.repository.project;

import com.nhnacademy.taskApi.dto.project.response.ProjectGetResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface ProjectRepositoryCustom {

    Page<ProjectGetResponse> getProjectsByUserId(Pageable pageable, String id);
    boolean existsProjectByUserId(String userId, String projectName);
}
