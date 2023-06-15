package com.nhnacademy.taskApi.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nhnacademy.taskApi.domain.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TaskResponse {

    private Long taskId;
    private Long projectId;
    private String taskName;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM.dd HH:mm")
    private LocalDateTime registerDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM.dd HH:mm")
    private LocalDateTime endDate;

    public TaskResponse(Task task) {
        this.taskId = task.getTaskId();
        this.projectId = task.getProject().getProjectId();
        this.taskName = task.getTaskName();
        this.content = task.getContent();
        this.registerDate = task.getRegisterDate();
        this.endDate = task.getEndDate();
    }
}
