package com.nhnacademy.taskApi.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nhnacademy.taskApi.domain.Task;
import com.nhnacademy.taskApi.dto.comment.response.CommentResponseDto;
import com.nhnacademy.taskApi.dto.tag.response.TagDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TaskResponse {

    private Long taskId;
    private Long projectId;
    private String taskName;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM.dd HH:mm")
    private LocalDateTime registerDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM.dd HH:mm")
    private LocalDateTime endDate;

    private List<TagDto> tagDtoList;
    private List<CommentResponseDto> commentResponseDtoList;

    public TaskResponse(Task task, List<TagDto> tagDtoList, List<CommentResponseDto> commentResponseDtoList ) {
        this.taskId = task.getTaskId();
        this.projectId = task.getProject().getProjectId();
        this.taskName = task.getTaskName();
        this.content = task.getContent();
        this.registerDate = task.getRegisterDate();
        this.endDate = task.getEndDate();
        this.tagDtoList = tagDtoList;
        this.commentResponseDtoList = commentResponseDtoList;
    }

    public TaskResponse(Task task) {
        this.taskId = task.getTaskId();
        this.projectId = task.getProject().getProjectId();
        this.taskName = task.getTaskName();
        this.content = task.getContent();
        this.registerDate = task.getRegisterDate();
        this.endDate = task.getEndDate();
    }
}
