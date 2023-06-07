package com.nhnacademy.taskApi.service;

import com.nhnacademy.taskApi.domain.Authority;
import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.dto.request.AuthorityRequest;
import com.nhnacademy.taskApi.dto.response.AuthorityResponse;
import com.nhnacademy.taskApi.exception.NotFindProjectException;
import com.nhnacademy.taskApi.repository.AuthorityRepository;
import com.nhnacademy.taskApi.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;
    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    @Override
    public List<AuthorityResponse> findAuthorities(Long projectId) {
        final List<Authority> authorityList = authorityRepository.findByPk_ProjectId(projectId).orElseThrow();
        return authorityList.stream()
                .map(authority -> new AuthorityResponse(authority.getPk().getUserId(), authority.getPk().getProjectId(), authority.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public AuthorityResponse registerMember(Long projectId, AuthorityRequest request) {
        final Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new NotFindProjectException("해당 프로젝트가 존재하지 않습니다.")
        );
        final Authority authority = new Authority(
                new Authority.Pk(request.getUserId(), request.getProjectId()),
                project,
                request.getRole()
        );
        authorityRepository.saveAndFlush(authority);

        return new AuthorityResponse(authority.getPk().getUserId(), authority.getPk().getProjectId(), authority.getRole());
    }

    @Override
    public String deleteMember(Long projectId, Long userId) {
        final Authority member = authorityRepository.findByPk_ProjectIdAndPk_UserId(projectId, userId).orElseThrow(
                () -> new NotFindProjectException("해당 프로젝트가 존재하지 않습니다.")
        );
        authorityRepository.delete(member);

        return "{\"result\":\"OK\"}";
    }
}
