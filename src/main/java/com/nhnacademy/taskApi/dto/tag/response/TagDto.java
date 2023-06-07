package com.nhnacademy.taskApi.dto.tag.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TagDto {
    private Long tagId;
    private String tagName;
    private Long taskId;

    @QueryProjection
    public TagDto(Long tagId, String tagName, Long taskId){
        this.tagId = tagId;
        this.tagName = tagName;
        this.taskId = taskId;
    }
}
