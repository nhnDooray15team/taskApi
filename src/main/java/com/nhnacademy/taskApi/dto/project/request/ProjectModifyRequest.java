package com.nhnacademy.taskApi.dto.project.request;

import lombok.Getter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;

@Getter
public class ProjectModifyRequest {
    @NotBlank(message = "프로젝트 이름을 입력해주세요")
    @Size(max = 45)
    private String projectName;

    @Nullable
    private String projectDescription;
    @NotBlank(message = "프로젝트의 상태를 설정해주세요")
    private String statusName;
}
