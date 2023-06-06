package com.nhnacademy.taskApi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityResponse {
    private Long userId;
    private Long projectId;
    private String role;
}
