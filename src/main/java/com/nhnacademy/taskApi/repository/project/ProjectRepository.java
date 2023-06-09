package com.nhnacademy.taskApi.repository.project;

import com.nhnacademy.taskApi.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

<<<<<<< HEAD:src/main/java/com/nhnacademy/taskApi/repository/ProjectRepository.java

public interface ProjectRepository extends JpaRepository<Project, Long> {
=======
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

    Optional<List<Project>> getAllBy();
>>>>>>> dev-version-0.2:src/main/java/com/nhnacademy/taskApi/repository/project/ProjectRepository.java

}
