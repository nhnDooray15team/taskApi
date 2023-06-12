package com.nhnacademy.taskApi.service.task;

import com.nhnacademy.taskApi.domain.Milestones;
import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.domain.Tag;
import com.nhnacademy.taskApi.domain.Task;
import com.nhnacademy.taskApi.dto.comment.response.CommentResponseDto;
import com.nhnacademy.taskApi.dto.tag.response.TagDto;
import com.nhnacademy.taskApi.dto.task.TaskRequest;
import com.nhnacademy.taskApi.dto.task.TaskResponse;
import com.nhnacademy.taskApi.exception.NotFoundException;
import com.nhnacademy.taskApi.repository.comment.CommentRepository;
import com.nhnacademy.taskApi.repository.milestone.MilestonesRepository;
import com.nhnacademy.taskApi.repository.project.ProjectRepository;
import com.nhnacademy.taskApi.repository.tag.TagRepository;
import com.nhnacademy.taskApi.repository.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskService{

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final MilestonesRepository milestonesRepository;
    private final CommentRepository commentRepository;
    private final  TagRepository tagRepository;

    @Transactional
    public void createTask(Long projectId, TaskRequest taskRequest) {
        final Project project = projectRepository.findById(projectId).orElseThrow();
        final Milestones milestones = milestonesRepository.findById(taskRequest.getMileStoneId()).orElseThrow();

        taskRepository.save(
                new Task(
                        taskRequest.getTaskName(),
                        taskRequest.getContent(),
                        LocalDateTime.now(),
                        taskRequest.getEndDate(),
                        project,
                        milestones
                ));
    }

    public TaskResponse getProjectTask(Long projectId, Long taskId) {
         Task task = taskRepository.findByProject_ProjectIdAndTaskId(projectId, taskId).orElseThrow(
                () -> new NotFoundException("해당 업무가 존재하지 않습니다.")
        );

         List<TagDto> tagDtoList = tagRepository.findByTaskId(taskId);
         List<CommentResponseDto> commentResponseDtoList = commentRepository.findByTaskId(taskId);
        return new TaskResponse(task,tagDtoList, commentResponseDtoList);
    }

    public List<TaskResponse> getProjectTaskList(Long projectId) {
        final List<Task> tasks = taskRepository.findAllByProject_ProjectId(projectId).orElseThrow(
                () -> new NotFoundException("해당 프로젝트의 업무가 존재하지 않습니다.")
        );
        return tasks.stream()
                .map(t -> new TaskResponse(
                        (Task) tasks))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteProjectTask(Long projectId, Long taskId) {
        taskRepository.deleteByProject_ProjectIdAndTaskId(projectId, taskId);
    }

    @Transactional
    public TaskResponse modifyProjectTask(Long projectId, Long taskId, TaskRequest request) {
        final Task task = taskRepository.findByProject_ProjectIdAndTaskId(projectId, taskId).orElseThrow(
                () -> new NotFoundException("해당 프로젝트의 업무가 존재하지 않습니다.")
        );

        task.setTaskName(request.getTaskName());
        task.setContent(request.getContent());

        taskRepository.save(task);

        return new TaskResponse(task);

    }

}
