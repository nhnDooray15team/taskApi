package com.nhnacademy.taskApi.dto.milestone.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
public class MilestonesRequest {

    @NotBlank(message = "마일스톤이름을 입력해주세요")
    @Size(max = 45)
    private String mileStoneName;

    @Nullable
    private LocalDate startDate;
    @Nullable
    private LocalDate endDate;
}
