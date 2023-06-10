package com.nhnacademy.taskApi.dto.authority.response;

import com.nhnacademy.taskApi.domain.Authority;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthorityDto {
    private String userId;
    private Authority.Role role;

    @QueryProjection
    public AuthorityDto(String userId, Authority.Role role){
        this.userId = userId;
        this.role = role;
    }
}
