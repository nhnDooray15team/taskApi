package com.nhnacademy.taskApi.repository.milestone;

import com.nhnacademy.taskApi.domain.Milestones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MilestonesRepository extends JpaRepository<Milestones, Long> {

    Optional<List<Milestones>> findAllByProject_ProjectId(Long projectId);

}
