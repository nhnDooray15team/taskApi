package com.nhnacademy.taskApi.repository.task;

import com.nhnacademy.taskApi.dto.comment.response.CommentResponseDto;
import com.nhnacademy.taskApi.dto.tag.response.TagDto;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface TaskRepositoryCustom {

    List<TagDto> findByTaskId(Long taskId);

}
