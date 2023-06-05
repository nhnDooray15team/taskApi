package com.nhnacademy.taskApi.repository;

import com.nhnacademy.taskApi.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, String> {
}
