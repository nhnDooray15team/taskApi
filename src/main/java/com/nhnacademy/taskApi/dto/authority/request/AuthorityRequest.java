package com.nhnacademy.taskApi.dto.authority.request;
import lombok.Getter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class AuthorityRequest {
    @NotBlank(message = "userId를 입력하세요.")
    @Size(max = 45, message = "userId는 45자 내외여야 합니다.")
    private String userId;
}
