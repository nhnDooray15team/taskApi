package com.nhnacademy.taskApi.controller;

import com.nhnacademy.taskApi.domain.Authority;
import com.nhnacademy.taskApi.dto.authority.request.AuthorityRequest;
import com.nhnacademy.taskApi.dto.authority.response.AuthorityDto;
import com.nhnacademy.taskApi.repository.AuthorityRepository;
import com.nhnacademy.taskApi.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects}")
public class AuthorityController {
    private final AuthorityService authorityService;

    @GetMapping("/{projectId}/authorities")
    public List<AuthorityDto> getAuthorities(@PathVariable("projectId") Long projectId){
        return authorityService.getAuthoritiesByProject(projectId);
    }

    @PostMapping("/{projectId}/authorities")
    public void registerAuthority(@PathVariable("projectId") Long projectId, @RequestBody @Valid AuthorityRequest authorityRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException();
        }
        authorityService.insertAuthority(projectId, authorityRequest);
    }

    @DeleteMapping("/{projectId}/authorities/{userId}")
    public void removeAuthority(@PathVariable("projectId") Long projectId, @PathVariable("userId") String userId){
        authorityService.deleteAuthority(projectId, userId);
    }
}
