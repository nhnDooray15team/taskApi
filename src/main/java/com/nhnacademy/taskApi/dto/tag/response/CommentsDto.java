package com.nhnacademy.taskApi.dto.tag.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentsDto {
    private Long commentsId;
    private String writerId;
    private String content;
    private LocalDateTime registerDate;

    @QueryProjection
    public CommentsDto(Long commentsId, String writerId, String content, LocalDateTime registerDate){
        this.commentsId = commentsId;
        this.writerId = writerId;
        this.content = content;
        this.registerDate = registerDate;
    }
}
