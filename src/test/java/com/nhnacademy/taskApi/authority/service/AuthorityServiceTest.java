package com.nhnacademy.taskApi.authority.service;

import com.nhnacademy.taskApi.domain.Authority;
import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.dto.authority.request.AuthorityRequest;
import com.nhnacademy.taskApi.dto.authority.response.AuthorityDto;
import com.nhnacademy.taskApi.exception.NotFoundException;
import com.nhnacademy.taskApi.repository.authority.AuthorityRepository;
import com.nhnacademy.taskApi.repository.project.ProjectRepository;
import com.nhnacademy.taskApi.service.authority.AuthorityService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class AuthorityServiceTest {

    @InjectMocks
    private AuthorityService authorityService;

    @Mock
    private AuthorityRepository authorityRepository;

    @Mock
    private ProjectRepository projectRepository;

    @Test
    @DisplayName("프로젝트에 대한 Authority 리스트 조회 - 프로젝트 존재")
    void getAuthorityList_ProjectExists_ReturnsAuthorityList() {
        // Given
        Long projectId = 1L;
        List<AuthorityDto> expectedAuthorityList = new ArrayList<>();
        // Set up the expectedAuthorityList with necessary data

        Mockito.when(authorityRepository.existsByProjectId(projectId)).thenReturn(true);
        Mockito.when(authorityRepository.getAuthorityDtoByProjectId(projectId)).thenReturn(expectedAuthorityList);

        // When
        List<AuthorityDto> result = authorityService.getAuthorityList(projectId);

        // Then
        assertEquals(expectedAuthorityList, result);
        Mockito.verify(authorityRepository, Mockito.times(1)).existsByProjectId(projectId);
        Mockito.verify(authorityRepository, Mockito.times(1)).getAuthorityDtoByProjectId(projectId);
        Mockito.verifyNoMoreInteractions(authorityRepository);
    }

    @Test
    @DisplayName("프로젝트에 대한 Authority 리스트 조회 - 프로젝트 미존재")
    void getAuthorityList_ProjectNotExists_ThrowsNotFoundException() {
        // Given
        Long projectId = 1L;

        Mockito.when(authorityRepository.existsByProjectId(projectId)).thenReturn(false);

        // When, Then
        assertThrows(NotFoundException.class, () -> authorityService.getAuthorityList(projectId));
        Mockito.verify(authorityRepository, Mockito.times(1)).existsByProjectId(projectId);
        Mockito.verifyNoMoreInteractions(authorityRepository);
    }

    @Test
    @DisplayName("Authority 추가")
    void insertAuthorityTest() {
        // Given
        Long projectId = 1L;
        AuthorityRequest authorityRequest = new AuthorityRequest();
        ReflectionTestUtils.setField(authorityRequest,"userId","user123");

        Project project = new Project();
        // Set up the project entity with necessary data

        Mockito.when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        // When
        authorityService.insertAuthority(projectId, authorityRequest);

        // Then
        ArgumentCaptor<Authority> captor = ArgumentCaptor.forClass(Authority.class);
        Mockito.verify(authorityRepository, Mockito.times(1)).save(captor.capture());

        Authority savedAuthority = captor.getValue();

        // Assertions for savedAuthority properties
        assertEquals(authorityRequest.getUserId(), savedAuthority.getPk().getUserId());
        assertEquals(projectId, savedAuthority.getPk().getProjectId());
        assertEquals(project, savedAuthority.getProject());
        assertEquals(Authority.Role.MEMBER, savedAuthority.getRole());

        Mockito.verify(projectRepository, Mockito.times(1)).findById(projectId);
        Mockito.verifyNoMoreInteractions(projectRepository);
        Mockito.verify(authorityRepository, Mockito.times(1)).save(Mockito.any(Authority.class));
        Mockito.verifyNoMoreInteractions(authorityRepository);
    }

    @Test
    @DisplayName("Authority 삭제")
    void deleteAuthorityTest() {
        // Given
        Long projectId = 1L;
        String userId = "user123";

        Mockito.when(authorityRepository.existsById(new Authority.Pk(userId, projectId))).thenReturn(true);

        // When
        authorityService.deleteAuthority(projectId, userId);

        // Then
        Mockito.verify(authorityRepository, Mockito.times(1)).existsById(new Authority.Pk(userId, projectId));
        Mockito.verify(authorityRepository, Mockito.times(1)).deleteById(new Authority.Pk(userId, projectId));
        Mockito.verifyNoMoreInteractions(authorityRepository);
    }

    @Test
    @DisplayName("Authority 삭제 - 해당 멤버가 없음")
    void deleteAuthority_MemberNotExists_ThrowsNotFoundException() {
        // Given
        Long projectId = 1L;
        String userId = "user123";

        Mockito.when(authorityRepository.existsById(new Authority.Pk(userId, projectId))).thenReturn(false);

        // When, Then
        assertThrows(NotFoundException.class, () -> authorityService.deleteAuthority(projectId, userId));
        Mockito.verify(authorityRepository, Mockito.times(1)).existsById(new Authority.Pk(userId, projectId));
        Mockito.verifyNoMoreInteractions(authorityRepository);
    }

}
