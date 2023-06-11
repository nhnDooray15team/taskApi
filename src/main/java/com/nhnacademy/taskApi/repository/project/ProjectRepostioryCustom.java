package com.nhnacademy.taskApi.repository.project;

import com.nhnacademy.taskApi.dto.project.response.ProjectGetResponse;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ProjectRepostioryCustom {

    List<ProjectGetResponse> getAllProjectBy(String userid);

}
