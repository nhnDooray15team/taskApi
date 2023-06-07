package com.nhnacademy.taskApi.repository;


import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.domain.Tag;
import com.nhnacademy.taskApi.dto.tag.response.TagResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long>, TagRepositoryCustom{
    List<TagResponseDto> findAllByProject_ProjectId(@Param("projectId") Long projectId);

}
