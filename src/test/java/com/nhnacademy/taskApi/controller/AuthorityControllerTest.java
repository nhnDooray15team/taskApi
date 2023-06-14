package com.nhnacademy.taskApi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.taskApi.domain.Authority;
import com.nhnacademy.taskApi.dto.authority.request.AuthorityRequest;
import com.nhnacademy.taskApi.dto.authority.response.AuthorityDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;


@WebMvcTest(AuthorityController.class)
class AuthorityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthorityController authorityController;

    @Test
    @DisplayName("공백 입력시 validate 확인")
    void whenNullValue_thenReturn400() throws Exception {
        final AuthorityRequest request = new AuthorityRequest("");

        mockMvc.perform(post("/projects/{projectId}/authorities", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("프로젝트에 해당하는 멤버 리스트 반환")
    void getAuthoritiesByProject() throws Exception {
        final AuthorityDto request = new AuthorityDto("testUserId", Authority.Role.MEMBER);
        List<AuthorityDto> list = List.of(request);
        given(authorityController.getAuthoritiesByProject(1L)).willReturn(list);

        mockMvc.perform(get("/projects/{projectId}/authorities", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].userId", is(request.getUserId())))
                .andExpect(jsonPath("$[0].role", is(request.getRole().toString())));
    }

    @Test
    void registerAuthority() throws Exception {
        final AuthorityRequest request = new AuthorityRequest("testUserId");

        mockMvc.perform(post("/projects/{projectId}/authorities", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

    }

    @Test
    void removeAuthority() throws Exception {
        mockMvc.perform(delete("/projects/{projectId}/authorities/user/{userId}", 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}