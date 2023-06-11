package com.nhnacademy.taskApi.dto.tag.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class TagRequest {
    @NotBlank(message = "태그 이름을 입력하세요")
    @Size(max = 45)
    private String tagName;
}
