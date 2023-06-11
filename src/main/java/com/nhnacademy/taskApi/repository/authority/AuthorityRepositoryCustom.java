package com.nhnacademy.taskApi.repository.authority;

import com.nhnacademy.taskApi.dto.authority.response.AuthorityDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
@NoRepositoryBean
public interface AuthorityRepositoryCustom {
    List<AuthorityDto> getAuthorityDtoByProjectId(Long projectId);
    boolean existsByProjectId(Long projectId);
}
