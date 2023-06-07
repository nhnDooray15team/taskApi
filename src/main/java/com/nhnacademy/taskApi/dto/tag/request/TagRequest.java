package com.nhnacademy.taskApi.dto.tag.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class TagRequest {
    @NotBlank(message = "태그 이름을 입력하세요.")
    private String tagName;
}
