package com.nhnacademy.taskApi.service.tag;

import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.domain.Tag;
import com.nhnacademy.taskApi.dto.tag.request.TagRequest;
import com.nhnacademy.taskApi.dto.tag.response.TagDto;
import com.nhnacademy.taskApi.dto.tag.response.TagResponseDto;
import com.nhnacademy.taskApi.exception.DuplicatedException;
import com.nhnacademy.taskApi.exception.InvalidRequestException;
import com.nhnacademy.taskApi.exception.NotFoundException;
import com.nhnacademy.taskApi.repository.tag.TagRepository;
import com.nhnacademy.taskApi.repository.project.ProjectRepository;
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
            throw new DuplicatedException("이미 존재하는 태그이름입니다. 변경해주세요");
        }
        Project project = projectRepository.findById(projectId)
                .orElseThrow(()-> new NotFoundException("프로젝트를 찾을수 없습니다."));
        tagRepository.save(new Tag(tagRequest.getTagName(), project));
    }
    @Transactional(readOnly = true)
    public List<TagResponseDto> getTagsByProject(Long projectId){
        return tagRepository.findAllByProject_ProjectId(projectId);
    }

    @Transactional(readOnly = true)
    public List<TagDto> getTagsByTask(Long taskId){
        return tagRepository.findByTaskId(taskId);
    }

    public void updateTag(Long projectId,Long tagId, TagRequest tagRequest){
        if(tagRepository.existsByProjectIdAndTagName(projectId, tagRequest.getTagName())){
            throw new NotFoundException("이미 존재하는 태그이름입니다. 변경해주세요");
        }
        final Tag tag = tagRepository.findById(tagId).orElseThrow(()->new NotFoundException("태그 데이터를 찾을수 없습니다."));
        tag.setTagName(tagRequest.getTagName());
    }
    public void removeTag(Long tagId){
        if(!tagRepository.existsById(tagId)){
            throw new InvalidRequestException("태그 데이터를 찾을수 없습니다.");
        }
        tagRepository.deleteById(tagId);
    }
}