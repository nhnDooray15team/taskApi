package com.nhnacademy.taskApi.dto.comment.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CommentRequest {
    @NotBlank(message = "글을 작성해주세요")
    private String content;
}
