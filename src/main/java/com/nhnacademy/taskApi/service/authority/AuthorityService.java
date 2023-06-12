package com.nhnacademy.taskApi.service.authority;

import com.nhnacademy.taskApi.domain.Authority;
import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.dto.authority.request.AuthorityRequest;
import com.nhnacademy.taskApi.dto.authority.response.AuthorityDto;
import com.nhnacademy.taskApi.exception.NotFoundException;
import com.nhnacademy.taskApi.repository.authority.AuthorityRepository;
import com.nhnacademy.taskApi.repository.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorityService {
    private final AuthorityRepository authorityRepository;
    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public List<AuthorityDto> getAuthorityList(Long projectId){
        if(!authorityRepository.existsByProjectId(projectId)){
            throw new NotFoundException("해당 프로젝트가 존재하지 않습니다.");
        }
        return authorityRepository.getAuthorityDtoByProjectId(projectId);
    }

    public void insertAuthority(Long projectId, AuthorityRequest authorityRequest){
        final Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundException("해당 프로젝트가 존재하지 않습니다."));

        authorityRepository.save(new Authority(new Authority.Pk(authorityRequest.getUserId(), projectId), project, Authority.Role.MEMBER));
    }

    public void deleteAuthority(Long projectId, String userId){
        if(!authorityRepository.existsById(new Authority.Pk(userId, projectId))){
            throw new NotFoundException("해당 멤버가 없습니다.");
        }
        authorityRepository.deleteById(new Authority.Pk(userId, projectId));
    }
}
