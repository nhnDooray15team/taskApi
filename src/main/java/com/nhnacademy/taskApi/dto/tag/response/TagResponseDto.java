package com.nhnacademy.taskApi.dto.tag.response;

import com.nhnacademy.taskApi.domain.Tag;
import lombok.Data;


public interface TagResponseDto {
    Long getTagId();
    String getTagName();
}
