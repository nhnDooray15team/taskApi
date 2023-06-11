package com.nhnacademy.taskApi.controller;

import com.nhnacademy.taskApi.dto.tag.request.TagRequest;
import com.nhnacademy.taskApi.dto.tag.response.TagDto;
import com.nhnacademy.taskApi.dto.tag.response.TagResponseDto;
import com.nhnacademy.taskApi.service.tag.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

/**
 * Tag와 관련된 동작들을 제어하는 Controller
 */
@RestController
@RequestMapping("/projects/{projectId}")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;
    /**
     *
     * @param projectId 해당 프로젝트 아이디
     * @return 프로젝트 별 태그들을 가져옴.
     */
    @GetMapping("/tags")
    public List<TagResponseDto> getAllTags(@PathVariable("projectId") Long projectId){
        return tagService.getTagsByProject(projectId);
    }

    /**
     *
     * @param projectId 해당 프로젝트 아이디, 업무에 프로젝트 아이디가 존재하므로 따로 서비스 단에 인자값으로 넣어주지 않아도 됨.
     * @param taskId 해당 태스크 아이디. 해당 업무는 프로젝트 내에 존재하므로 taskId만 사용하면 됨.
     * @return 업무 별 tag들을 가져옴.
     */
    @GetMapping("/tasks/{taskId}/tags")
    public List<TagDto> getTagsByTaskId(@PathVariable("projectId") Long projectId,
                                        @PathVariable("taskId") Long taskId){
        return tagService.getTagsByTask(taskId);
    }

    /**
     *
     * @param projectId 해당 프로젝트 아이디
     * @param tagRequest tag 내용이 담겨서 넘어옴
     * @param bindingResult tag내용을 적을때 빈칸으로 넘어오지 않도록, valid한 결과값
     * @throws ValidationException valid 결과 error발생 시
     */
    @PostMapping("/tags")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerTag(@PathVariable("projectId") Long projectId,
                            @RequestBody @Valid TagRequest tagRequest,
                            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException(); //exception 처리 추가하기
        }
        tagService.insertTag(projectId, tagRequest);
    }
    @PatchMapping("/tags/{tagId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTag(@PathVariable("projectId") Long projectId,
                          @PathVariable("tagId") Long tagId,
                          @RequestBody @Valid TagRequest tagRequest,
                          BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException(); //exception 처리 추가하기
        }
        tagService.updateTag(projectId,tagId,tagRequest);
    }

    @DeleteMapping("/tags/{tagId}")
    public void deleteTag(@PathVariable("projectId") Long projectId,
                          @PathVariable("tagId") Long tagId){
        tagService.removeTag(tagId);
    }

}
