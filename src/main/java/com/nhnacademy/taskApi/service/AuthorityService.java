package com.nhnacademy.taskApi.service;

import com.nhnacademy.taskApi.domain.Authority;
import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.dto.authority.request.AuthorityRequest;
import com.nhnacademy.taskApi.dto.authority.response.AuthorityDto;
import com.nhnacademy.taskApi.repository.AuthorityRepository;
import com.nhnacademy.taskApi.repository.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityService {
    private final AuthorityRepository authorityRepository;
    private final ProjectRepository projectRepository;

    public List<AuthorityDto> getAuthoritiesByProject(Long projectId){
        if(!authorityRepository.existsByPk_ProjectId(projectId)){
            throw new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다.");
        }
        return authorityRepository.findByPk_ProjectId(projectId);
    }

    public void insertAuthority(Long projectId, AuthorityRequest authorityRequest){
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));
        authorityRepository.save(new Authority(new Authority.Pk(authorityRequest.getUserId(), projectId), project, authorityRequest.getRole()));
    }

    public void deleteAuthority(Long projectId, String userId){
        if(!authorityRepository.existsById(new Authority.Pk(userId, projectId))){
            throw new IllegalArgumentException("해당 멤버가 없습니다.");
        }
        authorityRepository.deleteById(new Authority.Pk(userId, projectId));
    }
}
