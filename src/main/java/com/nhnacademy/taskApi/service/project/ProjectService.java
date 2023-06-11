package com.nhnacademy.taskApi.service.project;

import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.domain.ProjectStatus;
import com.nhnacademy.taskApi.dto.project.request.ProjectModifyRequest;
import com.nhnacademy.taskApi.dto.project.request.ProjectRequest;
import com.nhnacademy.taskApi.dto.project.response.ProjectGetResponse;
import com.nhnacademy.taskApi.exception.project.ProjectCreateException;
import com.nhnacademy.taskApi.exception.project.ProjectNotFountIdException;
import com.nhnacademy.taskApi.exception.project.ProjectNotFountStatusIdException;
import com.nhnacademy.taskApi.repository.ProjectStatusRepository;
import com.nhnacademy.taskApi.repository.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

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
                () -> new ProjectNotFountIdException("프로젝트 아이디를 찾을수 없습니다.")
        );

        ProjectStatus projectStatus = projectStatusRepository.findById(projectModifyRequest.getStatusId()).orElseThrow(
                () -> new ProjectNotFountStatusIdException("요청하신 상태정보를 찾을수 없습니다.")
        );

        project.setProjectStatus(projectStatus);
    }

    public List<ProjectGetResponse> getAllProjectBy(String userid) {
        return projectRepository.getAllProjectBy(userid);
    }

}
