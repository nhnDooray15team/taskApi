package com.nhnacademy.taskApi.repository;

import com.nhnacademy.taskApi.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Authority.Pk> {
}
