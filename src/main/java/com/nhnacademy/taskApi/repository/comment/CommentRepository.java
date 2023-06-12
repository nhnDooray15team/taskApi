package com.nhnacademy.taskApi.repository.comment;

import com.nhnacademy.taskApi.domain.Comment;
import com.nhnacademy.taskApi.dto.comment.response.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> , CommentRepositoryCustom{
    List<CommentDto> findAllByTask_TaskId(@Param("taskId") Long taskId);
}
