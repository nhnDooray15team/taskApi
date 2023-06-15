package com.nhnacademy.taskApi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.taskApi.dto.project.request.ProjectModifyRequest;
import com.nhnacademy.taskApi.dto.project.request.ProjectRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProjectController projectController;

    @Test
    void insertProject() throws Exception {
        final ProjectRequest request = new ProjectRequest();
        request.setProjectName("testName");
        request.setProjectDescription("testContent");

        mockMvc.perform(post("/projects/user/{userId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void modifyStatus() throws Exception {
        final ProjectModifyRequest request = new ProjectModifyRequest();
        request.setProjectDescription("description");
        request.setStatusName("DORMANT");
        request.setProjectName("testProject");

        mockMvc.perform(patch("/projects/{projectId}/status", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}