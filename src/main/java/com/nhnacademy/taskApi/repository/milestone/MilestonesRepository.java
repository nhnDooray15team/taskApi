package com.nhnacademy.taskApi.repository.milestone;

import com.nhnacademy.taskApi.domain.Milestones;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface MilestonesRepository extends JpaRepository<Milestones, Long> {

    Optional<List<Milestones>> findAllByProject_ProjectId(Long projectId);

}
