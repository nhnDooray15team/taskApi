package com.nhnacademy.taskApi.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TaskRequest {
    private Long mileStoneId;
    private String taskName;
    private String content;
    private LocalDateTime endDate;
}
