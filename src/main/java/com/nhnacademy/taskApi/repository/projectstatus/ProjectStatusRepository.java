package com.nhnacademy.taskApi.repository.projectstatus;

import com.nhnacademy.taskApi.domain.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Long> {
    ProjectStatus findByStatusName(@Param("statusName")ProjectStatus.StatusName statusName);
}
