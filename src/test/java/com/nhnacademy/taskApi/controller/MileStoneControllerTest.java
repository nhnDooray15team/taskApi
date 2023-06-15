package com.nhnacademy.taskApi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.taskApi.dto.authority.request.AuthorityRequest;
import com.nhnacademy.taskApi.dto.milestone.request.MilestonesModifyRequest;
import com.nhnacademy.taskApi.dto.milestone.request.MilestonesRequest;
import com.nhnacademy.taskApi.dto.milestone.response.MilestonesResponse;
import org.junit.jupiter.api.DisplayName;
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
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MileStoneController.class)
class MileStoneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MileStoneController mileStoneController;

    @Test
    void getAllMilestones() throws Exception {
        final MilestonesResponse request = new MilestonesResponse(1L, 1L, "testName", LocalDate.now(), LocalDate.now());
        List<MilestonesResponse> list = List.of(request);

        given(mileStoneController.getAllMilestones(1L)).willReturn(list);

        mockMvc.perform(get("/projects/{projectId}/milestones", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].mileStoneName", is(request.getMileStoneName())));
    }

    @Test
    void createMilestone() throws Exception {
        final MilestonesRequest request = new MilestonesRequest("testMilestone", LocalDate.now(), LocalDate.now());

        mockMvc.perform(post("/projects/{projectId}/milestone", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void modifyMilestone() throws Exception {
        final MilestonesModifyRequest request = new MilestonesModifyRequest();
        request.setMileStoneName("test");
        request.setStartDate(LocalDate.now());
        request.setEndDate(LocalDate.now());

        mockMvc.perform(patch("/projects/{projectId}/milestones/{milestonesId}", 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

    }

    @Test
    void deleteMilestone() throws Exception {
        mockMvc.perform(delete("/projects/{projectId}/milestones/{milestonesId}", 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("공백 입력시 validate 확인")
    void whenNullValue_thenReturn400() throws Exception {
        final MilestonesRequest request = new MilestonesRequest("", null, null);

        mockMvc.perform(post("/projects/{projectId}/milestone", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}