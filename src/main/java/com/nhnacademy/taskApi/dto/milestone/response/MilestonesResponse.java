package com.nhnacademy.taskApi.dto.milestone.response;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
public class MilestonesResponse {
    private Long mileStoneId;
    private Long projectId;
    private String mileStoneName;
    private LocalDate startDate;
    private LocalDate endDate;
}
