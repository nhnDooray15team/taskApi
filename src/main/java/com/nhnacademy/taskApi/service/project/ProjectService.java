package com.nhnacademy.taskApi.service.project;

import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.domain.ProjectStatus;
import com.nhnacademy.taskApi.dto.project.request.ProjectGetResponse;
import com.nhnacademy.taskApi.dto.project.request.ProjectModifyRequest;
import com.nhnacademy.taskApi.dto.project.request.ProjectRequest;
import com.nhnacademy.taskApi.exception.project.ProjectCreateException;
import com.nhnacademy.taskApi.exception.project.ProjectNotFoundList;
import com.nhnacademy.taskApi.exception.project.ProjectNotFountId;
import com.nhnacademy.taskApi.exception.project.ProjectNotFountStatusId;
import com.nhnacademy.taskApi.repository.ProjectStatusRepository;
import com.nhnacademy.taskApi.repository.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectStatusRepository projectStatusRepository;


    @Transactional
    public void createProject(ProjectRequest projectCreateRequest) {
        ProjectStatus projectStatus = projectStatusRepository.findById(projectCreateRequest.getStatusId()).orElseThrow(
                ()-> new ProjectCreateException("상태 값이 존재하지않습니다.")
        );

        projectRepository.save(
                new Project(projectCreateRequest.getProjectId(), projectCreateRequest.getProjectName(),
                        projectCreateRequest.getProjectDescription(), projectStatus)
        );
    }

    @Transactional
    public void modifyProject(Long projectId, ProjectModifyRequest projectModifyRequest) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new ProjectNotFountId("프로젝트 아이디를 찾을수 없습니다.")
        );

        ProjectStatus projectStatus = projectStatusRepository.findById(projectModifyRequest.getStatusId()).orElseThrow(
                () -> new ProjectNotFountStatusId("요청하신 상태정보를 찾을수 없습니다.")
        );

        project.setProjectStatus(projectStatus);
    }

    public List<ProjectGetResponse> getAllProject() {
        Optional<List<Project>> projects = Optional.ofNullable(projectRepository.getAllBy().orElseThrow(
                () -> new ProjectNotFoundList("프로젝트 목록들이 비어있습니다.")
        ));

        return projects.get()
                .stream()
                .map(p -> new ProjectGetResponse(
                        p.getProjectId(),
                        p.getProjectName(),
                        p.getProjectStatus().getStatusId()))
                .collect(Collectors.toList());
    }


}
