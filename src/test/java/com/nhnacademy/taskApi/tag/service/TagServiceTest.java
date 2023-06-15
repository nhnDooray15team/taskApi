package com.nhnacademy.taskApi.tag.service;

import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.domain.Tag;
import com.nhnacademy.taskApi.dto.tag.request.TagRequest;
import com.nhnacademy.taskApi.dto.tag.response.TagDto;
import com.nhnacademy.taskApi.dto.tag.response.TagResponseDto;
import com.nhnacademy.taskApi.exception.DuplicatedException;
import com.nhnacademy.taskApi.exception.InvalidRequestException;
import com.nhnacademy.taskApi.repository.project.ProjectRepository;
import com.nhnacademy.taskApi.repository.tag.TagRepository;
import com.nhnacademy.taskApi.service.tag.TagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    @Mock
    private TagRepository tagRepository;
    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private TagService tagService;


    private Project project1;

    private TagRequest tagRequest1;
    private TagRequest tagRequest2;
    private TagRequest tagRequest3;

    private Tag tag1;
    private Tag tag2;
    private Tag tag3;

    private

    @BeforeEach
    void setUp() {

        tagRequest1 = new TagRequest();
        ReflectionTestUtils.setField(tagRequest1,"tagName","tag1");


        tagRequest2 = new TagRequest();
        ReflectionTestUtils.setField(tagRequest2,"tagName","tag2");

        tagRequest3 = new TagRequest();
        ReflectionTestUtils.setField(tagRequest3,"tagName","tag3");

        project1 = new Project();
        ReflectionTestUtils.setField(project1,"projectId",1L);
        ReflectionTestUtils.setField(project1,"projectName","Java-백엔드3기교육");
        ReflectionTestUtils.setField(project1,"projectDescription","java 백엔드3기 교육설명입니다.");


        tag1 = new Tag();
        ReflectionTestUtils.setField(tag1,"tagId", 1L );
        ReflectionTestUtils.setField(tag1,"tagName", tagRequest1.getTagName() );
        ReflectionTestUtils.setField(tag1,"project", project1);

        tag2 = new Tag();
        ReflectionTestUtils.setField(tag2,"tagId", 2L );
        ReflectionTestUtils.setField(tag2,"tagName", tagRequest2.getTagName() );
        ReflectionTestUtils.setField(tag2,"project", project1);

        tag3 = new Tag();
        ReflectionTestUtils.setField(tag3,"tagId", 3L );
        ReflectionTestUtils.setField(tag3,"tagName", tagRequest3.getTagName() );
        ReflectionTestUtils.setField(tag3,"project", project1);


    }

    @Test
    @DisplayName("해당 프로젝트 ID에 tag 추가하기")
    void insertTag() {

         Long projectId1 = 1L;
        TagRequest insertTag = new TagRequest();
        ReflectionTestUtils.setField(insertTag,"tagName","tag5");

        when(tagRepository.existsByProjectIdAndTagName(projectId1, insertTag.getTagName())).thenReturn(false);

        Project project = new Project();
        when(projectRepository.findById(projectId1)).thenReturn(Optional.of(project));

        Tag tag = new Tag();
        when(tagRepository.save(any(Tag.class))).thenReturn(tag);

        tagService.insertTag(projectId1, insertTag);

        ArgumentCaptor<Tag> tagCapter = ArgumentCaptor.forClass(Tag.class);
        verify(tagRepository,times(1)).save(tagCapter.capture());

        Tag savedtag = tagCapter.getValue();

        assertEquals(insertTag.getTagName(), savedtag.getTagName());


    }

    @Test
    @DisplayName("해당 프로젝트 ID에 tag 추가하기 - 이미 존재하는 태그이름")
    void insertTagException() {

        Long projectId1 = 1L;
        TagRequest insertTag = new TagRequest();
        ReflectionTestUtils.setField(insertTag,"tagName","tag5");

        when(tagRepository.existsByProjectIdAndTagName(projectId1, insertTag.getTagName())).thenReturn(true);

       assertThrows(DuplicatedException.class, () -> tagService.insertTag(projectId1,insertTag));


    }


    @Test
    @DisplayName("해당 프로젝트내에 존재하는 tag 목록")
    void getTagsByProject() {
        List<TagResponseDto> tagResponseDtoList = new ArrayList<>();
        tagResponseDtoList.add(new TagResponseDto() {
            @Override
            public Long getTagId() {
                return tag1.getTagId();
            }

            @Override
            public String getTagName() {
                return tag1.getTagName();
            }
        });

        tagResponseDtoList.add(new TagResponseDto() {
            @Override
            public Long getTagId() {
                return tag2.getTagId();
            }

            @Override
            public String getTagName() {
                return tag2.getTagName();
            }
        });

        tagResponseDtoList.add(new TagResponseDto() {
            @Override
            public Long getTagId() {
                return tag2.getTagId();
            }

            @Override
            public String getTagName() {
                return tag2.getTagName();
            }
        });

        when(tagRepository.findAllByProject_ProjectId(project1.getProjectId())).thenReturn(tagResponseDtoList);

        List<TagResponseDto> result = tagService.getTagsByProject(1L);

        assertEquals(tagResponseDtoList, result);

    }

    @Test
    @DisplayName("해당 프로젝트내에 있는 업무별 tag 목록")
    void getTagsByTask() {

        Long taskId = 1L;
        List<TagDto> expectedTags = new ArrayList<>();


        when(tagRepository.findByTaskId(taskId)).thenReturn(expectedTags);


        List<TagDto> result = tagService.getTagsByTask(taskId);

        assertEquals(expectedTags, result);
        verify(tagRepository, times(1)).findByTaskId(taskId);
        verifyNoMoreInteractions(tagRepository);

    }



    @Test
    @DisplayName("해당 프로젝트내에 있는 tag 변경")
    void updateTag() {
        TagRequest modifyTagreq = new TagRequest();
        when(tagRepository.existsByProjectIdAndTagName(project1.getProjectId(),modifyTagreq.getTagName())).thenReturn(false);

        when(tagRepository.findById(tag1.getTagId())).thenReturn(Optional.ofNullable(tag1));

        tagService.updateTag(project1.getProjectId(), tag1.getTagId(), modifyTagreq);

    }

    @Test
    void removeTag() {
        when(tagRepository.existsById(tag1.getTagId())).thenReturn(false);

        assertThrows(InvalidRequestException.class, () -> tagService.removeTag(tag1.getTagId()));

        verify(tagRepository, times(1)).existsById(tag1.getTagId());
        verifyNoMoreInteractions(tagRepository);
    }
}