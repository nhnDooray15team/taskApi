package com.nhnacademy.taskApi.service;

import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.domain.ProjectStatus;
import com.nhnacademy.taskApi.dto.project.ProjectRequest;
import com.nhnacademy.taskApi.exception.NotFoundProjectStatus;
import com.nhnacademy.taskApi.repository.ProjectRepository;
import com.nhnacademy.taskApi.repository.ProjectStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl {

    private ProjectRepository projectRepository;
    private ProjectStatusRepository projectStatusRepository;

    public void createProject(ProjectRequest request) {

        final Long statusId = request.getStatusId();
        final Optional<ProjectStatus> projectStatus = projectStatusRepository.findById(statusId);
        projectStatus.orElseThrow(
                () -> new NotFoundProjectStatus("해당 프로젝트 상태가 없습니다.")
        );



        new Project()


    }
}
