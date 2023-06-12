package com.nhnacademy.taskApi.repository.tag;

import com.nhnacademy.taskApi.domain.Tag;
import com.nhnacademy.taskApi.dto.tag.response.TagDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface TagRepositoryCustom {
    boolean existsByProjectIdAndTagName(Long projectId, String tagName);
    List<TagDto> findByTaskId(Long taskId);
}
