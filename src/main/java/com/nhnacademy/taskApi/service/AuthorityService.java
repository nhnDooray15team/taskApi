package com.nhnacademy.taskApi.service;

import com.nhnacademy.taskApi.dto.request.AuthorityRequest;
import com.nhnacademy.taskApi.dto.response.AuthorityResponse;

import java.util.List;

public interface AuthorityService {
    List<AuthorityResponse> findAuthorities(Long projectId);
    AuthorityResponse registerMember(Long projectId, AuthorityRequest request);
    String deleteMember(Long projectId, Long userId);
}
