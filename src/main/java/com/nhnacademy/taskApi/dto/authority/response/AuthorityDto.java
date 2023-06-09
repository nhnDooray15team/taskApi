package com.nhnacademy.taskApi.dto.authority.response;

import com.nhnacademy.taskApi.domain.Authority;

public interface AuthorityDto {
    String getUserId();
    Authority.Role getRole();
}
