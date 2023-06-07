package com.nhnacademy.taskApi.service.tag;

import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.domain.Tag;
import com.nhnacademy.taskApi.dto.tag.request.TagRequest;
import com.nhnacademy.taskApi.dto.tag.response.TagDto;
import com.nhnacademy.taskApi.dto.tag.response.TagResponseDto;
import com.nhnacademy.taskApi.repository.ProjectRepository;
import com.nhnacademy.taskApi.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * insertTag : 태그 생성, 같은 프로젝트 내에서 같은 태그명을 쓸 수 없음.
 * getTagsByProject : project 별 태그 리스트
 * getTagsByTask : task 별 태그 리스트
 * updateTag : 해당 프로젝트에 바꾸려고 하는 태그가 이미 존재한다면 바꾸지 못하도록, 없다면 해당 태그의 이름을 변경함.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class TagService {
    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;

    public void insertTag(Long projectId, TagRequest tagRequest){
        if(tagRepository.existsByProjectIdAndTagName(projectId, tagRequest.getTagName())){
            throw new IllegalArgumentException("Already Exists Tag. Change the Name");
        }
        Project project = projectRepository.findById(projectId)
                .orElseThrow(()-> new IllegalArgumentException("Don't search the Project"));
        tagRepository.save(new Tag(tagRequest.getTagName(), project));
    }
    @Transactional(readOnly = true)
    public List<TagResponseDto> getTagsByProject(Long projectId){
        return tagRepository.findAllByProject_ProjectId(projectId);
    }

    @Transactional
    public List<TagDto> getTagsByTask(Long taskId){
        return tagRepository.findByTaskId(taskId);
    }

    public void updateTag(Long projectId,Long tagId, TagRequest tagRequest){
        if(tagRepository.existsByProjectIdAndTagName(projectId, tagRequest.getTagName())){
            throw new IllegalArgumentException("Already Exists Tag. Change the name, please");
        }
        Tag tag = tagRepository.findById(tagId).orElseThrow(()->new IllegalArgumentException("No such tag data"));
        tag.setTagName(tagRequest.getTagName());
    }
    public void removeTag(Long tagId){
        if(!tagRepository.existsById(tagId)){
            throw new IllegalArgumentException("No such Tag");
        }
        tagRepository.deleteById(tagId);
    }
}