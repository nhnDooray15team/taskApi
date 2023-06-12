package com.nhnacademy.taskApi.service.project;

import com.nhnacademy.taskApi.domain.Authority;
import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.domain.ProjectStatus;
import com.nhnacademy.taskApi.dto.project.request.ProjectModifyRequest;
import com.nhnacademy.taskApi.dto.project.request.ProjectRequest;
import com.nhnacademy.taskApi.dto.project.response.ProjectGetResponse;
import com.nhnacademy.taskApi.exception.DuplicatedException;
import com.nhnacademy.taskApi.exception.NotFoundException;
import com.nhnacademy.taskApi.repository.projectstatus.ProjectStatusRepository;
import com.nhnacademy.taskApi.repository.authority.AuthorityRepository;
import com.nhnacademy.taskApi.repository.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectStatusRepository projectStatusRepository;
    private final AuthorityRepository authorityRepository;

    public void createProject(ProjectRequest request, String userId) {
        if(projectRepository.existsProjectByUserId(userId, request.getProjectName())){
            throw new DuplicatedException("이미 존재하는 프로젝트 이름입니다.");
        }
        final ProjectStatus projectStatus = projectStatusRepository.findByStatusName(ProjectStatus.StatusName.ACTIVATED);
        final Project project = projectRepository.save(new Project(request.getProjectName(),request.getProjectDescription(),projectStatus));
        authorityRepository.save(new Authority(new Authority.Pk(userId, project.getProjectId()),project, Authority.Role.ADMIN));
    }

    public void modifyProject(Long projectId, ProjectModifyRequest projectModifyRequest) {
        final Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new NotFoundException("프로젝트 아이디를 찾을수 없습니다.")
        );
        final ProjectStatus projectStatus = projectStatusRepository.findByStatusName(ProjectStatus.StatusName.valueOf(projectModifyRequest.getStatusName()));
        project.setProjectName(projectModifyRequest.getProjectName());
        project.setProjectDescription(projectModifyRequest.getProjectDescription());
        project.setProjectStatus(projectStatus);
    }

    @Transactional(readOnly = true)
    public Page<ProjectGetResponse> getAllProject(Pageable pageable, String id) {
        return projectRepository.getProjectsByUserId(pageable, id);
    }
}
