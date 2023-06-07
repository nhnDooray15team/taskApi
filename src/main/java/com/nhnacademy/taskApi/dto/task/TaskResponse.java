package com.nhnacademy.taskApi.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TaskResponse {

    private Long taskId;
    private Long projectId;
    private String taskName;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM.dd HH:mm")
    private LocalDateTime registerDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM.dd HH:mm")
    private LocalDateTime endDate;
}
