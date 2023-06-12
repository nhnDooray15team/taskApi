package com.nhnacademy.taskApi.dto.comment.response;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private Long commentId;
    private String writerId;
    private String content;
    private LocalDateTime registerDate;

    @QueryProjection
    public CommentResponseDto(Long commentId, String writerId, String content, LocalDateTime registerDate) {
        this.commentId = commentId;
        this.writerId = writerId;
        this.content = content;
        this.registerDate = registerDate;
    }
}
