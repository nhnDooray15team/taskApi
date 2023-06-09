package com.nhnacademy.taskApi.service.project;


<<<<<<< HEAD
import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.dto.project.request.ProjectRequest;
import com.nhnacademy.taskApi.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
=======
import com.nhnacademy.taskApi.dto.project.request.ProjectCreateRequest;
import com.nhnacademy.taskApi.dto.project.request.ProjectGetResponse;
import com.nhnacademy.taskApi.dto.project.request.ProjectModifyRequest;

import java.util.List;
>>>>>>> dev-version-0.2

@Service
@RequiredArgsConstructor
public class ProjectService{
    private final ProjectRepository projectRepository;

<<<<<<< HEAD

    public void createProject(ProjectRequest projectRequest) {
        // TODO 2 : 관계에서 외래키같은거 어떻게 처리해야하는지 ..
        projectRepository.save(
                new Project(projectRequest.getProjectId(), projectRequest.getProjectName(),
                        projectRequest.getProjectDescription(), projectRequest.getStatusId())
        );
    }
=======
public interface ProjectService {
    void createProject(ProjectCreateRequest projectCreateRequest);

    void modifyProject(Long projectId, ProjectModifyRequest projectModifyRequest);


    List<ProjectGetResponse> getAllProject();
>>>>>>> dev-version-0.2
}
