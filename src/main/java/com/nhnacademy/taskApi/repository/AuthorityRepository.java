package com.nhnacademy.taskApi.repository;

import com.nhnacademy.taskApi.domain.Authority;
import com.nhnacademy.taskApi.dto.authority.response.AuthorityDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Authority.Pk> {
    List<AuthorityDto> findByPk_ProjectId(@Param("projectId") Long projectId);
    boolean existsByPk_ProjectId(@Param("projectId") Long projectId);
}
