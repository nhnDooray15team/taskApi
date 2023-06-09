package com.nhnacademy.taskApi.dto.comment.response;

import java.time.LocalDateTime;

public interface CommentDto {
    Long getCommentsId();
    String getWriterId();
    String getContent();
    LocalDateTime getRegisterDate();
}
