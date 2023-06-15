package com.nhnacademy.taskApi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.taskApi.dto.comment.request.CommentRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommentController commentController;



    @Test
    void registerComment() throws Exception {
        final CommentRequest request = new CommentRequest();
        request.setContent("testContent");
        mockMvc.perform(post("/projects/{projectId}/tasks/{taskId}/user/{userId}/comments", 1L, 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void modifyComment() throws Exception {
        final CommentRequest request = new CommentRequest();
        request.setContent("testContent");

        mockMvc.perform(post("/projects/{projectId}/tasks/{taskId}/user/{userId}/comments", 1L, 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void removeComment() throws Exception {
        mockMvc.perform(delete("/projects/{projectId}/tasks/{taskId}/comments/{commentsId}", 1L, 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Request 객체 @Valid 확인")
    void registerValidCheck() throws Exception {
        final CommentRequest request = new CommentRequest();
        mockMvc.perform(post("/projects/{projectId}/tasks/{taskId}/user/{userId}/comments", 1L, 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}