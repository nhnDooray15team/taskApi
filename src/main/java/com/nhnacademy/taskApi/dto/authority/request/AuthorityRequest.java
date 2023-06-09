package com.nhnacademy.taskApi.dto.authority.request;

import com.nhnacademy.taskApi.domain.Authority;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class AuthorityRequest {
    @NotNull
    private String userId;
    private Authority.Role role;
}
