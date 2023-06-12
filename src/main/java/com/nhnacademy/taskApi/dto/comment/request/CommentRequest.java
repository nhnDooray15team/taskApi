package com.nhnacademy.taskApi.dto.comment.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class CommentRequest {
    @NotBlank(message = "글을 작성해주세요")
    @Size(max = 250)
    private String content;
}
