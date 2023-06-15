package com.nhnacademy.taskApi.dto.project.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ProjectRequest {
    @NotBlank(message = "프로젝트 이름을 입력해주세요")
    private String projectName;

    @Nullable
    private String projectDescription;
}
