package com.nhnacademy.taskApi.repository;

import com.nhnacademy.taskApi.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findById(Long userId);

    Optional<List<Authority>> findByPk_ProjectId(Long Id);

    Optional<Authority> findByPk_ProjectIdAndPk_UserId(Long projectId, Long userId);

}
