package com.nhnacademy.taskApi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityRequest {
    private Long userId;
    private Long projectId;
    private String role;
}
