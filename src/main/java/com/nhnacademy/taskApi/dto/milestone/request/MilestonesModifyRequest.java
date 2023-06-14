package com.nhnacademy.taskApi.dto.milestone.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
public class MilestonesModifyRequest {

    @NotBlank(message = "마일스톤 이름을 작성해주세요")
    @Size(max = 45, message = "마일스톤 이름은 45자 내외여야 합니다.")
    private String mileStoneName;

    @Nullable
    private LocalDate startDate;

    @Nullable
    private LocalDate endDate;
}
