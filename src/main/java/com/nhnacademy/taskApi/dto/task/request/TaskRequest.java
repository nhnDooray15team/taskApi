package com.nhnacademy.taskApi.dto.task.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TaskRequest {

    @NotBlank(message = "업무 이름을 입력하세요")
    private String taskName;

    @Nullable
    private String content;

    @Nullable
    private LocalDateTime endDate;

    private Long milestoneId;

}
