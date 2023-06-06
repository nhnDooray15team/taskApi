package com.nhnacademy.taskApi.repository;

import com.nhnacademy.taskApi.domain.Milestones;
import com.nhnacademy.taskApi.dto.milestone.response.MilestonesResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MilestonesRepository extends JpaRepository<Milestones, Integer> {
    List<MilestonesResponse> findAllByProject_ProjectId(Integer projectId);
}
