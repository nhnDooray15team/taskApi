package com.nhnacademy.taskApi.repository;

import com.nhnacademy.taskApi.domain.Milestones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MilestonesRepository extends JpaRepository<Milestones, Integer> {
}
