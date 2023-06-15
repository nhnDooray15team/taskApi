package com.nhnacademy.taskApi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.taskApi.domain.Task;
import com.nhnacademy.taskApi.dto.task.TaskResponse;
import com.nhnacademy.taskApi.dto.task.TaskTagCommentResponse;
import com.nhnacademy.taskApi.dto.task.request.TaskRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaskController taskController;

    @Test
    void createTask() throws Exception {
        final TaskRequest taskRequest = new TaskRequest("testName", "testContent", LocalDateTime.now(), 1L);

        mockMvc.perform(post("/projects/{projectId}/tasks", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskRequest)))
                .andExpect(status().isCreated());

        verify(taskController, atLeastOnce()).createTask(anyLong(), any(taskRequest.getClass()));
    }

    @Test
    void getProjectTask() throws Exception {
        given(taskController.getProjectTask(anyLong(), anyLong())).willReturn(new TaskTagCommentResponse());

        mockMvc.perform(get("/projects/{projectId}/tasks/{taskId}", anyLong(), anyLong())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(taskController, atLeastOnce()).getProjectTask(anyLong(), anyLong());
    }

    @Test
    void getProjectTaskList() throws Exception {
        final TaskResponse taskResponse = new TaskResponse();
        List<TaskResponse> list = List.of(taskResponse);
        given(taskController.getProjectTaskList(anyLong())).willReturn(list);

        mockMvc.perform(get("/projects/{projectId}/tasks", anyLong())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(list)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        verify(taskController, atLeastOnce()).getProjectTaskList(anyLong());
    }

    @Test
    void deleteProjectTask() throws Exception {
        mockMvc.perform(delete("/projects/projectId/tasks/{taskId}", anyLong())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(taskController, atLeastOnce()).deleteProjectTask(anyLong());
    }

    @Test
    void updateProjectTask() throws Exception{
        final TaskRequest taskRequest = new TaskRequest("testName", "testContent", LocalDateTime.now(), 1L);

        mockMvc.perform(patch("/projects/{projectId}/tasks/{taskId}", 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskRequest)))
                .andExpect(status().isOk());

        verify(taskController, atLeastOnce()).updateProjectTask(anyLong(), anyLong(), any(taskRequest.getClass()));
    }
}