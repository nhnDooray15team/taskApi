package com.nhnacademy.taskApi.task;

import com.nhnacademy.taskApi.domain.Milestones;
import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.domain.Task;
import com.nhnacademy.taskApi.dto.comment.response.CommentResponseDto;
import com.nhnacademy.taskApi.dto.tag.response.TagDto;
import com.nhnacademy.taskApi.dto.task.TaskResponse;
import com.nhnacademy.taskApi.dto.task.TaskTagCommentResponse;
import com.nhnacademy.taskApi.dto.task.request.TaskRequest;
import com.nhnacademy.taskApi.exception.NotFoundException;
import com.nhnacademy.taskApi.repository.comment.CommentRepository;
import com.nhnacademy.taskApi.repository.milestone.MilestonesRepository;
import com.nhnacademy.taskApi.repository.project.ProjectRepository;
import com.nhnacademy.taskApi.repository.tag.TagRepository;
import com.nhnacademy.taskApi.repository.task.TaskRepository;
import com.nhnacademy.taskApi.service.task.TaskService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private MilestonesRepository milestonesRepository;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TagRepository tagRepository;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private TaskService taskService;

    @Captor
    private ArgumentCaptor<Task> taskArgumentCaptor;

    @Test
    @DisplayName("Create Task")
    void createTask() {
        TaskRequest taskRequest = new TaskRequest("Task 1", "Task content", LocalDateTime.now().plusDays(7), 1L);

        Project project = new Project();
        ReflectionTestUtils.setField(project, "projectId", 1L);

        Optional<Project> optionalProject = Optional.of(project);
        Milestones milestones = new Milestones();
        Optional<Milestones> optionalMilestones = Optional.of(milestones);

        when(projectRepository.findById(project.getProjectId())).thenReturn(optionalProject);
        when(milestonesRepository.findById(taskRequest.getMilestoneId())).thenReturn(optionalMilestones);

        taskService.createTask(project.getProjectId(), taskRequest);

        verify(taskRepository, times(1)).save(any(Task.class));

        verify(projectRepository, times(1)).findById(project.getProjectId());
        verify(milestonesRepository, times(1)).findById(taskRequest.getMilestoneId());

        reset(taskRepository, projectRepository, milestonesRepository);
    }


    @Test
    @DisplayName("해당 업무 조회")
    void getProjectTask() {
        Long taskId = 1L;
        Task task = new Task();
        Project project = new Project();

        ReflectionTestUtils.setField(project, "projectId", 1L);
        ReflectionTestUtils.setField(task, "project", project);

        List<TagDto> tagDtoList = new ArrayList<>();
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();


        when(taskRepository.findByProject_ProjectIdAndTaskId(project.getProjectId(), taskId)).thenReturn(Optional.of(task));
        when(tagRepository.findByTaskId(taskId)).thenReturn(tagDtoList);
        when(commentRepository.findByTaskId(taskId)).thenReturn(commentResponseDtoList);
//
        taskService.getProjectTask(project.getProjectId(), taskId);

        verify(taskRepository, times(1)).findByProject_ProjectIdAndTaskId(project.getProjectId(), taskId);
        verify(tagRepository, times(1)).findByTaskId(taskId);
        verify(commentRepository, times(1)).findByTaskId(taskId);
        verifyNoMoreInteractions(taskRepository, tagRepository, commentRepository);
    }

    @Test
    @DisplayName("해당 프로젝트의 업무 리스트")
    void getProgetProjectTaskList() {
        Long projectId = 1L;
        List<Task> tasks = new ArrayList<>();
        List<TaskResponse> expectedResponseList = new ArrayList<>();

        when(taskRepository.findAllByProject_ProjectId(projectId)).thenReturn(Optional.of(tasks));


        List<TaskResponse> result = taskService.getProjectTaskList(projectId);

        assertEquals(expectedResponseList, result);
        verify(taskRepository, times(1)).findAllByProject_ProjectId(projectId);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    @DisplayName("업무 삭제")
    void deleteProjectTask() {
        Long taskId = 1L;

        when(taskRepository.existsById(taskId)).thenReturn(true);

        assertDoesNotThrow(() -> taskService.deleteProjectTask(taskId));

        verify(taskRepository, times(1)).existsById(taskId);
        verify(taskRepository, times(1)).deleteByTaskId(taskId);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    @DisplayName("업무 삭제 - 업무 아이디 없음")
    void deleteProjectTaskException() {
        Long taskId = 1L;

        when(taskRepository.existsById(taskId)).thenReturn(false);

        assertThrows(ValidationException.class, () -> taskService.deleteProjectTask(taskId));

        verify(taskRepository, times(1)).existsById(taskId);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    @DisplayName("해당 프로젝트의 업무를 수정")
    void modifyProjectTask() {
        Long projectId = 1L;
        Long taskId = 1L;
        TaskRequest request = new TaskRequest("업무요청", "업무에에관한 요청입니다.",
                LocalDateTime.of(2023, 6, 23, 12, 0, 0), 1L);
        Task task = new Task();

        when(taskRepository.findByProject_ProjectIdAndTaskId(projectId, taskId)).thenReturn(Optional.of(task));

        assertDoesNotThrow(() -> taskService.modifyProjectTask(projectId, taskId, request));

        verify(taskRepository, times(1)).findByProject_ProjectIdAndTaskId(projectId, taskId);
        verify(taskRepository, times(1)).save(taskArgumentCaptor.capture());
        verifyNoMoreInteractions(taskRepository);

        Task modifiedTask = taskArgumentCaptor.getValue();
        assertEquals(request.getTaskName(), modifiedTask.getTaskName());
        assertEquals(request.getContent(), modifiedTask.getContent());
    }

    @Test
    @DisplayName("해당 프로젝트의 업무를 수정 - 프로젝트의 업무가 존재하지않음")
    void modifyProjectTaskExcption() {
        Long projectId = 1L;
        Long taskId = 1L;
        TaskRequest request = new TaskRequest("업무요청", "업무에에관한 요청입니다.",
                LocalDateTime.of(2023, 6, 23, 12, 0, 0), 1L);

        when(taskRepository.findByProject_ProjectIdAndTaskId(projectId, taskId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> taskService.modifyProjectTask(projectId, taskId, request));

        verify(taskRepository, times(1)).findByProject_ProjectIdAndTaskId(projectId, taskId);
        verifyNoMoreInteractions(taskRepository);
    }

}
