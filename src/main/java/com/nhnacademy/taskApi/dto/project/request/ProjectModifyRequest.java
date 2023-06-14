package com.nhnacademy.taskApi.dto.project.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;

@Getter
@Setter
public class ProjectModifyRequest {
    @NotBlank(message = "프로젝트 이름을 입력해주세요")
    @Size(max = 45, message = "프로젝트 이름은 45자 내외여야 합니다.")
    private String projectName;

    @Nullable
    private String projectDescription;
    @NotBlank(message = "프로젝트의 상태를 설정해주세요")
    private String statusName;
}
