package com.nhnacademy.taskApi.authority.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.taskApi.controller.AuthorityController;
import com.nhnacademy.taskApi.domain.Authority;
import com.nhnacademy.taskApi.dto.authority.request.AuthorityRequest;
import com.nhnacademy.taskApi.dto.authority.response.AuthorityDto;
import com.nhnacademy.taskApi.service.authority.AuthorityService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AuthorityController.class)
@DisplayName("AuthorityControllerTest")
class AuthorityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorityService authorityService;

    @Autowired
    private ObjectMapper objectMapper;

    private AuthorityDto authorityDto1;
    private AuthorityDto authorityDto2;
    private AuthorityDto authorityDto3;

    @BeforeEach
    void setUp() {

        authorityDto1 = new AuthorityDto("user1", Authority.Role.ADMIN);
        authorityDto2 = new AuthorityDto("user2", Authority.Role.MEMBER);
        authorityDto3 = new AuthorityDto("user3", Authority.Role.MEMBER);
    }

    @Test
    @DisplayName("프로젝트에 소속된 멤버 목록 조회")
    void getAuthoritiesByProject() throws Exception {
        given(authorityService.getAuthorityList(1L)).willReturn(Arrays.asList(authorityDto1,authorityDto2,authorityDto3));
        mockMvc.perform(get("/projects/"+1L+"/authorities"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(authorityDto1,authorityDto2,authorityDto3))))
                .andExpect(jsonPath("$[0].userId").value("user1"))
                .andExpect(jsonPath("$[0].role").value("ADMIN"))
                .andExpect(jsonPath("$[1].userId").value("user2"))
                .andExpect(jsonPath("$[1].role").value("MEMBER"))
                .andExpect(jsonPath("$[2].userId").value("user3"))
                .andExpect(jsonPath("$[2].role").value("MEMBER"));



    }

//    @Test
//    @DisplayName("해당 프로젝트에 멤버 등록")
//    void registerAuthority() throws Exception{
//        AuthorityRequest authorityRequest = new AuthorityRequest();
//        ReflectionTestUtils.setField(authorityRequest,"userId","userTest1");
//
//
////        given(authorityController.registerAuthority(1L, any(AuthorityRequest.class)).willReturn(new Authority(new Authority.Pk(authorityRequest.getUserId(),1L)));
//        mockMvc.perform(post("/projects/{projectId}/authorities", 1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(authorityRequest)))
//                .andExpect(status().isCreated());
//
//        verify(authorityController, atLeastOnce()).registerAuthority(anyLong(), any(authorityRequest.getClass()));
//    }
//
//    @Test
//    void checkValid() throws Exception {
//        AuthorityRequest authorityRequest = new AuthorityRequest();
//        ReflectionTestUtils.setField(authorityRequest,"userId","");
//
//        mockMvc.perform(post("/projects/{projectId}/authorities", 1L)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(authorityRequest)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void removeAuthority() {
//    }
}