package com.nhnacademy.taskApi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.taskApi.dto.tag.request.TagRequest;
import com.nhnacademy.taskApi.dto.tag.response.TagDto;
import com.nhnacademy.taskApi.dto.tag.response.TagResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
import static org.hamcrest.Matchers.is;


@WebMvcTest(TagController.class)
class TagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TagController tagController;


    @Test
    void getTagsByTaskId() throws Exception {
        final TagDto tag = new TagDto(1L, "test", 1L);
        List<TagDto> list = List.of(tag);

        given(tagController.getTagsByTaskId(1L, 1L)).willReturn(list);

        mockMvc.perform(get("/projects/{projectId}/tasks/{taskId}/tags", 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(list)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].tagName", is(tag.getTagName())));

        verify(tagController, atLeastOnce()).getTagsByTaskId(anyLong(), anyLong());
    }

    @Test
    void registerTag() throws Exception {
        final TagRequest request = new TagRequest();
        request.setTagName("testTag");

        mockMvc.perform(post("/projects/{projectId}/tags", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        verify(tagController, atLeastOnce()).registerTag(anyLong(), any(request.getClass()));
    }

    @Test
    void updateTag() throws Exception {
        final TagRequest request = new TagRequest();
        request.setTagName("testTag");

        mockMvc.perform(patch("/projects/{projectId}/tags/{tagId}", 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
        verify(tagController, atLeastOnce()).updateTag(anyLong(), anyLong(), any(request.getClass()));

    }

    @Test
    void deleteTag() throws Exception {
        mockMvc.perform(delete("/projects/{projectId}/tags/{tagId}", anyLong(), anyLong())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(tagController, atLeastOnce()).deleteTag(anyLong(), anyLong());
    }
}