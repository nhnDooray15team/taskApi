package com.nhnacademy.taskApi.controller;

import com.nhnacademy.taskApi.dto.comment.request.CommentRequest;
import com.nhnacademy.taskApi.dto.comment.response.CommentDto;
import com.nhnacademy.taskApi.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects/{projectId}/tasks/{taskId}")
@Transactional
public class CommentController {
    private final CommentService commentService;

    @Transactional(readOnly = true)
    @GetMapping("/comments")
    public List<CommentDto> getComments(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId){
        return commentService.getCommentList(taskId);
    }

    @PostMapping("/user/{userId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerComment(@PathVariable("projectId") Long projectId, @PathVariable("taskId") Long taskId, @PathVariable("userId") String userId,
                                @RequestBody @Valid CommentRequest commentRequest){

        commentService.insertComment(taskId, userId, commentRequest);
    }

    @PatchMapping("/comments/{commentsId}")
    @ResponseStatus(HttpStatus.OK)
    public void modifyComment(@PathVariable("projectId") Long projectId,
                              @PathVariable("taskId") Long taskId,
                              @PathVariable("commentsId") Long commentsId,
                              @RequestBody @Valid CommentRequest commentRequest){

        commentService.updateComment(commentsId, commentRequest);
    }

    @DeleteMapping("/comments/{commentsId}")
    public void removeComment(@PathVariable("projectId") Long projectId,
                              @PathVariable("taskId") Long taskId,
                              @PathVariable("commentsId") Long commentsId){
        commentService.deleteComment(commentsId);
    }
}
