package com.nhnacademy.taskApi.service.comment;

import com.nhnacademy.taskApi.domain.Comment;
import com.nhnacademy.taskApi.domain.Task;
import com.nhnacademy.taskApi.dto.comment.request.CommentRequest;
import com.nhnacademy.taskApi.dto.comment.response.CommentDto;
import com.nhnacademy.taskApi.exception.InvalidRequestException;
import com.nhnacademy.taskApi.exception.NotFoundException;
import com.nhnacademy.taskApi.repository.comment.CommentRepository;
import com.nhnacademy.taskApi.repository.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<CommentDto> getCommentList(Long taskId){

        return commentRepository.findAllByTask_TaskId(taskId);
    }

    public void insertComment(Long taskId, String userId, CommentRequest commentRequest){
        final Task task = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException("해당하는 업무가 없습니다."));
        commentRepository.save(new Comment(null, userId, commentRequest.getContent(), LocalDateTime.now(), task));
    }

    public void updateComment(Long commentsId, CommentRequest commentRequest){
        final Comment comment = commentRepository.findById(commentsId).orElseThrow(() -> new NotFoundException("해당하는 댓글이 없습니다."));
        comment.setContent(commentRequest.getContent());
        comment.setRegisterDate(LocalDateTime.now());
    }

    public void deleteComment(Long commentId){
        if(!commentRepository.existsById(commentId)){
            throw new InvalidRequestException("해당하는 댓글이 없습니다.");
        }
        commentRepository.deleteById(commentId);
    }
}
