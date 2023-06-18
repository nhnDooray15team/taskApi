package com.nhnacademy.taskApi.dto.milestone.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class MilestonesModifyRequest {

    @NotBlank(message = "마일스톤 이름을 작성해주세요")
    @Size(max = 45, message = "마일스톤 이름은 45자 내외여야 합니다.")
    private String mileStoneName;

    @Nullable
    private LocalDateTime startDate;

    @Nullable
    private LocalDateTime endDate;

//    public MilestonesModifyRequest(String mileStoneName, @Nullable LocalDateTime startDate, @Nullable LocalDateTime endDate) {
//        this.mileStoneName = mileStoneName;
//        this.startDate = startDate;
//        this.endDate = endDate;
//    }
}
