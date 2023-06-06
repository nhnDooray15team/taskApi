package com.nhnacademy.taskApi.AuthoritiesController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.taskApi.controller.AuthoritiesController;
import com.nhnacademy.taskApi.dto.request.AuthorityRequest;
import com.nhnacademy.taskApi.dto.response.AuthorityResponse;
import com.nhnacademy.taskApi.service.AuthorityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthoritiesController.class)
class AuthoritiesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorityService authorityService;

    @Test
    @DisplayName("프로젝트 멤버 등록")
    void resisterMember() throws Exception {
        final AuthorityResponse authorityResponse = new AuthorityResponse(1L, 1L, "멤버");
        final AuthorityRequest authorityRequest = new AuthorityRequest(1L, 1L, "멤버");
        String jsonBody = new ObjectMapper().writeValueAsString(authorityResponse);

        given(authorityService.registerMember(1L, authorityRequest)).willReturn(authorityResponse);

        mockMvc.perform(post("/projects/{projectId}/authorities", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("프로젝트 멤버 삭제")
    void deleteMember() throws Exception {
        final AuthorityRequest authorityRequest = new AuthorityRequest(1L, 1L, "멤버");
        String jsonBody = new ObjectMapper().writeValueAsString("OK");

        given(authorityService.deleteMember(1L, 1L)).willReturn("{\"result\":\"OK\"}");

        mockMvc.perform(delete("/projects/{projectId}/authorities/{userId}", 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk());

        verify(authorityService).deleteMember(1L, 1L);

    }
}