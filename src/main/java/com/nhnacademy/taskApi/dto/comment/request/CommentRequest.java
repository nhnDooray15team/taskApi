package com.nhnacademy.taskApi.dto.comment.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CommentRequest {
    @NotBlank(message = "글을 작성해주세요")
    private String content;
}
