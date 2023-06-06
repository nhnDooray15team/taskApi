package com.nhnacademy.taskApi.service;

import com.nhnacademy.taskApi.domain.Authority;
import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.dto.request.AuthorityRequest;
import com.nhnacademy.taskApi.dto.response.AuthorityResponse;
import com.nhnacademy.taskApi.repository.AuthorityRepository;
import com.nhnacademy.taskApi.repository.ProjectRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
class AuthorityServiceTest {

    @Autowired
    private AuthorityService authorityService;

    @MockBean
    private AuthorityRepository authorityRepository;

    @MockBean
    private ProjectRepository projectRepository;

    @Test
    @DisplayName("프로젝트 멤버 추가")
    void registerMember() {
        AuthorityRequest request = new AuthorityRequest(1L, 2L, "멤버");
        final Project project = projectRepository.findById(request.getProjectId()).orElseThrow();

        Authority member = new Authority(new Authority.Pk(request.getUserId(), request.getProjectId()), project, "멤버");

        given(projectRepository.findById(request.getProjectId())).willReturn(Optional.of(project));
        given(authorityRepository.saveAndFlush(member)).willReturn(member);

        final AuthorityResponse result = authorityService.registerMember(request.getProjectId(), request);
        assertThat(result.getProjectId()).isEqualTo(request.getProjectId());
        verify(projectRepository, atLeastOnce()).findById(request.getProjectId());
        verify(authorityRepository, times(1)).saveAndFlush(member);
    }

    @Test
    @DisplayName("프로젝트 멤버 삭제")
    void deleteMember() {
        final Project project = projectRepository.findById(1L).orElseThrow();
        final Authority result = authorityRepository.saveAndFlush(new Authority(new Authority.Pk(1L, 2L), project, "멤버"));

        final String deleteMember = authorityService.deleteMember(result.getPk().getProjectId(), result.getPk().getUserId());
        assertThat(deleteMember).isEqualTo("{\"result\":\"OK\"}");
    }

}