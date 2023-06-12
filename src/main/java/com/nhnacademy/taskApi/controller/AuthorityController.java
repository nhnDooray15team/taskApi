package com.nhnacademy.taskApi.controller;

import com.nhnacademy.taskApi.dto.authority.request.AuthorityRequest;
import com.nhnacademy.taskApi.dto.authority.response.AuthorityDto;
import com.nhnacademy.taskApi.service.authority.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects/{projectId}/authorities")
public class AuthorityController {
    private final AuthorityService authorityService;

    @GetMapping
    public List<AuthorityDto> getAuthoritiesByProject(@PathVariable("projectId") Long projectId){
        return authorityService.getAuthorityList(projectId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAuthority(@PathVariable("projectId") Long projectId,
                                  @RequestBody @Valid AuthorityRequest authorityRequest){

        authorityService.insertAuthority(projectId, authorityRequest);
    }

    @DeleteMapping("/user/{userId}")
    public void removeAuthority(@PathVariable("projectId") Long projectId,
                                @PathVariable("userId") String userId){
        authorityService.deleteAuthority(projectId, userId);
    }
}
