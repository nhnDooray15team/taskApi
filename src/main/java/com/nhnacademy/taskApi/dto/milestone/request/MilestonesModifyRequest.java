package com.nhnacademy.taskApi.dto.milestone.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class MilestonesModifyRequest {

    @NotBlank(message = "이름을 작성해주세요")
    @Size(max = 45)
    private String mileStoneName;

    @Nullable
    private LocalDate startDate;

    @Nullable
    private LocalDate endDate;
}
