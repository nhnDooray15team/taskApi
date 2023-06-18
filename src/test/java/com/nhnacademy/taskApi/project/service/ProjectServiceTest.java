package com.nhnacademy.taskApi.project.service;

import com.nhnacademy.taskApi.domain.Authority;
import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.domain.ProjectStatus;
import com.nhnacademy.taskApi.dto.project.request.ProjectModifyRequest;
import com.nhnacademy.taskApi.dto.project.request.ProjectRequest;
import com.nhnacademy.taskApi.dto.project.response.ProjectGetResponse;
import com.nhnacademy.taskApi.exception.DuplicatedException;
import com.nhnacademy.taskApi.repository.authority.AuthorityRepository;
import com.nhnacademy.taskApi.repository.project.ProjectRepository;
import com.nhnacademy.taskApi.repository.projectstatus.ProjectStatusRepository;
import com.nhnacademy.taskApi.service.project.ProjectService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {


    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private ProjectStatusRepository projectStatusRepository;
    @Mock
    private AuthorityRepository authorityRepository;

    @InjectMocks
    private ProjectService projectService;

    private String userId;
    private ProjectRequest projectRequest;

    private ProjectStatus projectStatus;
    private Project project1;
    private Project project2;

    private Authority authority1;

    private Authority.Pk authorityPk1;

    private Authority authority2;

    private Authority.Pk authorityPk2;

    private ProjectGetResponse projectGetResponse1;
    private ProjectGetResponse projectGetResponse2;

    @BeforeEach
    void setUp() {
        userId = "userTest";
        projectRequest = new ProjectRequest();
        ReflectionTestUtils.setField(projectRequest, "projectName", "프로젝트테스트");
        ReflectionTestUtils.setField(projectRequest, "projectDescription", "프로젝트설명입니다.");

        projectStatus = new ProjectStatus();
        ReflectionTestUtils.setField(projectStatus, "statusName", ProjectStatus.StatusName.ACTIVATED);

        project1 = new Project();
        ReflectionTestUtils.setField(project1, "projectId", 1L);
        ReflectionTestUtils.setField(project1, "projectName", projectRequest.getProjectName());
        ReflectionTestUtils.setField(project1, "projectDescription", projectRequest.getProjectDescription());
        ReflectionTestUtils.setField(project1, "projectStatus", projectStatus);

        authority1 = new Authority();
        ReflectionTestUtils.setField(authority1, "project", project1);
        ReflectionTestUtils.setField(authority1, "role", Authority.Role.ADMIN);

        authorityPk1 = new Authority.Pk();
        ReflectionTestUtils.setField(authorityPk1, "userId", userId);
        ReflectionTestUtils.setField(authorityPk1, "projectId", project1.getProjectId());


        project2 = new Project();
        ReflectionTestUtils.setField(project2, "projectId", 2L);
        ReflectionTestUtils.setField(project2, "projectName", projectRequest.getProjectName());
        ReflectionTestUtils.setField(project2, "projectDescription", projectRequest.getProjectDescription());
        ReflectionTestUtils.setField(project2, "projectStatus", projectStatus);

        authority2 = new Authority();
        ReflectionTestUtils.setField(authority2, "project", project2);
        ReflectionTestUtils.setField(authority2, "role", Authority.Role.MEMBER);

        authorityPk2 = new Authority.Pk();
        ReflectionTestUtils.setField(authorityPk2, "userId", userId);
        ReflectionTestUtils.setField(authorityPk2, "projectId", project2.getProjectId());

        projectGetResponse1 = new ProjectGetResponse();
        ReflectionTestUtils.setField(projectGetResponse1, "projectId", project1.getProjectId());
        ReflectionTestUtils.setField(projectGetResponse1, "projectName", project1.getProjectName());
        ReflectionTestUtils.setField(projectGetResponse1, "role", authority1.getRole());
        ReflectionTestUtils.setField(projectGetResponse1, "statusName", project1.getProjectStatus().getStatusName());

        projectGetResponse2 = new ProjectGetResponse();
        ReflectionTestUtils.setField(projectGetResponse2, "projectId", project2.getProjectId());
        ReflectionTestUtils.setField(projectGetResponse2, "projectName", project2.getProjectName());
        ReflectionTestUtils.setField(projectGetResponse2, "role", authority2.getRole());
        ReflectionTestUtils.setField(projectGetResponse2, "statusName", project2.getProjectStatus().getStatusName());


    }


    @Test
    @DisplayName("userId가 속한 프로젝트 리스트 조회")
    void getAllProject() {
        List<ProjectGetResponse> projects = new ArrayList<>();
        projects.add(projectGetResponse1);
        projects.add(projectGetResponse2);
        Pageable pageable = PageRequest.of(0, 10);


        Page<ProjectGetResponse> expectedProjectGetResponseList = new PageImpl<>(projects, pageable,projects.size() );

        when(projectRepository.getProjectsByUserId(pageable,userId)).thenReturn(expectedProjectGetResponseList);

        Page<ProjectGetResponse> result = projectService.getAllProject(pageable, userId);

        // Then
        assertEquals(expectedProjectGetResponseList.getTotalElements(), result.getTotalElements());
        assertEquals(expectedProjectGetResponseList.getNumber(), result.getNumber());
        assertEquals(expectedProjectGetResponseList.getSize(), result.getSize());


    }

    @Test
    @DisplayName("프로젝트 생성")
    void createProject() {

        when(projectRepository.existsProjectByUserId(userId, projectRequest.getProjectName())).thenReturn(false);

        when(projectStatusRepository.findByStatusName(projectStatus.getStatusName())).thenReturn(projectStatus);

        when(projectRepository.save(any(Project.class))).thenReturn(project1);

        when(authorityRepository.save(any(Authority.class))).thenReturn(authority1);


        projectService.createProject(projectRequest, userId);

        ArgumentCaptor<Project> projectCaptor = ArgumentCaptor.forClass(Project.class);
        verify(projectRepository, times(1)).save(projectCaptor.capture());

        Project savedProject = projectCaptor.getValue();
        assertEquals(projectRequest.getProjectName(), savedProject.getProjectName());
        assertEquals(projectRequest.getProjectDescription(), savedProject.getProjectDescription());
        assertEquals(projectStatus.getStatusName(), savedProject.getProjectStatus().getStatusName());


        ArgumentCaptor<Authority> authorityCapter = ArgumentCaptor.forClass(Authority.class);
        verify(authorityRepository, times(1)).save(authorityCapter.capture());

        Authority savedAuthority = new Authority();
        savedAuthority = authorityCapter.getValue();

        assertEquals(authority1.getProject(), savedAuthority.getProject());
        assertEquals(authority1.getRole(), savedAuthority.getRole());
        assertEquals(authorityPk1.getUserId(), savedAuthority.getPk().getUserId());
        assertEquals(authorityPk1.getProjectId(), savedAuthority.getPk().getProjectId());

    }

    @Test
    @DisplayName("프로젝트 생성 - 해당 프로젝트 존재")
    void createProjectException() {
        String userId = "userTest";
        ProjectRequest projectRequest = new ProjectRequest();
        ReflectionTestUtils.setField(projectRequest, "projectName", "프로젝트테스트");
        ReflectionTestUtils.setField(projectRequest, "projectDescription", "프로젝트설명입니다.");

        when(projectRepository.existsProjectByUserId(userId, projectRequest.getProjectName())).thenReturn(true);

        assertThrows(DuplicatedException.class, () -> projectService.createProject(projectRequest, userId));
    }

    @Test
    @DisplayName("해당 프로젝트 수정")
    void modifyProject() {
        ProjectModifyRequest projectModifyRequest = new ProjectModifyRequest();
        ReflectionTestUtils.setField(projectModifyRequest,"projectName","프로젝트 수정테스트입니다.");
        ReflectionTestUtils.setField(projectModifyRequest,"projectDescription","프로젝트 수정설명입니다.");
        ReflectionTestUtils.setField(projectModifyRequest,"statusName",ProjectStatus.StatusName.ENDED.toString());


        when(projectRepository.findById(project1.getProjectId())).thenReturn(Optional.of(project1));
        when(projectStatusRepository.findByStatusName(ProjectStatus.StatusName.valueOf(projectModifyRequest.getStatusName())))
                .thenReturn(projectStatus);

        projectService.modifyProject(project1.getProjectId(), projectModifyRequest);

        assertEquals(projectModifyRequest.getProjectName(), project1.getProjectName());
        assertEquals(projectModifyRequest.getProjectDescription(), project1.getProjectDescription());


    }


}