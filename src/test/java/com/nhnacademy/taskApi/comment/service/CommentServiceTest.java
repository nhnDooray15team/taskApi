package com.nhnacademy.taskApi.comment.service;

import com.nhnacademy.taskApi.domain.Comment;
import com.nhnacademy.taskApi.domain.Task;
import com.nhnacademy.taskApi.dto.comment.request.CommentRequest;
import com.nhnacademy.taskApi.dto.comment.response.CommentDto;
import com.nhnacademy.taskApi.exception.InvalidRequestException;
import com.nhnacademy.taskApi.exception.NotFoundException;
import com.nhnacademy.taskApi.repository.comment.CommentRepository;
import com.nhnacademy.taskApi.repository.task.TaskRepository;
import com.nhnacademy.taskApi.service.comment.CommentService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private CommentService commentService;

    private Long taskId = 1L;
    private String user = "userTest";


    @Test
    @DisplayName("업무에 대한 Comment 리스트 조회")
    void getCommentList() {

        List<CommentDto> expectedCommentDtoList = new ArrayList<>();

        Mockito.when(commentRepository.findAllByTask_TaskId(taskId)).thenReturn(expectedCommentDtoList);

        List<CommentDto> result = commentService.getCommentList(taskId);

        assertEquals(expectedCommentDtoList, result);
        Mockito.verify(commentRepository).findAllByTask_TaskId(taskId);
        Mockito.verifyNoMoreInteractions(commentRepository);

    }

    @Test
    @DisplayName("Comment 추가")
    void insertComment() {

        CommentRequest commentRequest = new CommentRequest();

        ReflectionTestUtils.setField(commentRequest, "content", "댓글입니다.");

        Task task = new Task();

        Mockito.when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        commentService.insertComment(taskId, user, commentRequest);

        ArgumentCaptor<Comment> captor = ArgumentCaptor.forClass(Comment.class);
        Mockito.verify(commentRepository, Mockito.times(1)).save(captor.capture());

        Comment savedComment = captor.getValue();

        assertEquals(commentRequest.getContent(), savedComment.getContent());
        assertEquals(user, savedComment.getWriterId());


        Mockito.verify(taskRepository, Mockito.times(1)).findById(taskId);
        Mockito.verifyNoMoreInteractions(taskRepository);

        Mockito.verify(commentRepository, Mockito.times(1)).save(Mockito.any(Comment.class));
        Mockito.verifyNoMoreInteractions(commentRepository);

    }

    @Test
    @DisplayName("Comment 수정")
    void updateComment() {
        // localdatetime에 대해 코드 수정 필요할수도..?

        Long commentId = 1L;
        CommentRequest commentRequest = new CommentRequest();
        Comment comment = new Comment();
        LocalDateTime time = LocalDateTime.now().withNano(0);

        ReflectionTestUtils.setField(comment, "content", commentRequest.getContent());
        ReflectionTestUtils.setField(comment, "registerDate", time);

        Mockito.when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));

        commentService.updateComment(commentId,commentRequest);

        assertEquals(commentRequest.getContent(), comment.getContent());
        assertEquals(comment.getRegisterDate().withNano(0), time);



    }

    @Test
    @DisplayName("Comment 삭제")
    void deleteComment() {
        Long commentId = 1L;

        Mockito.when(commentRepository.existsById(commentId)).thenReturn(true);

        commentService.deleteComment(commentId);

        Mockito.verify(commentRepository,Mockito.times(1) ).existsById(commentId);
        Mockito.verify(commentRepository,Mockito.times(1) ).deleteById(commentId);
        Mockito.verifyNoMoreInteractions(commentRepository);

    }

    @Test
    @DisplayName("Comment 삭제 - 해당 댓글이 없음")
    void deleteCommentException() {
        Long commentId = 2L;

        Mockito.when(commentRepository.existsById(commentId)).thenReturn(false);

        assertThrows(InvalidRequestException.class, () -> commentService.deleteComment(commentId ));
        Mockito.verify(commentRepository,Mockito.times(1) ).existsById(commentId);
        Mockito.verifyNoMoreInteractions(commentRepository);




    }
}