package com.nhnacademy.taskApi.service;

import com.nhnacademy.taskApi.domain.Comment;
import com.nhnacademy.taskApi.domain.Task;
import com.nhnacademy.taskApi.dto.comment.request.CommentRequest;
import com.nhnacademy.taskApi.dto.comment.response.CommentDto;
import com.nhnacademy.taskApi.repository.CommentRepository;
import com.nhnacademy.taskApi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    public List<CommentDto> getCommentList(Long taskId){
        if(!taskRepository.existsById(taskId)){
            throw new IllegalArgumentException("해당하는 업무가 없습니다.");
        }
        return commentRepository.findAllByTask_TaskId(taskId);
    }

    public void insertComment(Long taskId, String userId, CommentRequest commentRequest){
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("해당하는 업무가 없습니다."));
        commentRepository.save(new Comment(null, userId, commentRequest.getContent(), LocalDateTime.now(), task));
    }

    public void updateComment(Long commentsId, CommentRequest commentRequest){
        Comment comment = commentRepository.findById(commentsId).orElseThrow(() -> new IllegalArgumentException("해당하는 댓글이 없습니다."));
        comment.setContent(commentRequest.getContent());
        comment.setRegisterDate(LocalDateTime.now());
    }

    public void deleteComment(Long commentId){
        if(!commentRepository.existsById(commentId)){
            throw new IllegalArgumentException("해당하는 댓글이 없습니다.");
        }
        commentRepository.deleteById(commentId);
    }
}
