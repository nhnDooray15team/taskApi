package com.nhnacademy.taskApi.repository.milestone;

import com.nhnacademy.taskApi.domain.Milestones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

<<<<<<< HEAD:src/main/java/com/nhnacademy/taskApi/repository/MilestonesRepository.java
public interface MilestonesRepository extends JpaRepository<Milestones, Long> {
    List<MilestonesResponse> findAllByProject_ProjectId(Long projectId);
=======
@Repository
public interface MilestonesRepository extends JpaRepository<Milestones, Long> {

    Optional<List<Milestones>> findAllByProject_ProjectId(Long projectId);

>>>>>>> dev-version-0.2:src/main/java/com/nhnacademy/taskApi/repository/milestone/MilestonesRepository.java
}
