package com.nhnacademy.taskApi.dto.project.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class ProjectRequest {
    @NotBlank
    private String projectName;

    @Nullable
    private String projectDescription;
}
