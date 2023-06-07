package com.nhnacademy.taskApi.service.project;


import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.dto.project.request.ProjectRequest;
import com.nhnacademy.taskApi.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService{
    private final ProjectRepository projectRepository;


    public void createProject(ProjectRequest projectRequest) {
        // TODO 2 : 관계에서 외래키같은거 어떻게 처리해야하는지 ..
        projectRepository.save(
                new Project(projectRequest.getProjectId(), projectRequest.getProjectName(),
                        projectRequest.getProjectDescription(), projectRequest.getStatusId())
        );
    }
}
