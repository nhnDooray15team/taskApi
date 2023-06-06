package com.nhnacademy.taskApi.controller;

import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.dto.request.AuthorityRequest;
import com.nhnacademy.taskApi.dto.response.AuthorityResponse;
import com.nhnacademy.taskApi.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class AuthoritiesController {

    private final AuthorityService authorityService;

    @GetMapping("/{projectId}/authorities")
    public List<AuthorityResponse> getAuthorities(@PathVariable Long projectId) {
        return authorityService.findAuthorities(projectId);
    }

    @PostMapping("/{projectId}/authorities")
    public AuthorityResponse registerMember(@PathVariable Long projectId, @RequestBody AuthorityRequest request) {
        return authorityService.registerMember(projectId, request);
    }

    @DeleteMapping("/{projectId}/authorities/{userId}")
    public String deleteMember(@PathVariable Long projectId, @PathVariable Long userId) {
        return authorityService.deleteMember(projectId, userId);
    }

}
