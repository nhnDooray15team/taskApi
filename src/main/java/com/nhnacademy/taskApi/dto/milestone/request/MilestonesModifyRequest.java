package com.nhnacademy.taskApi.dto.milestone.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class MilestonesModifyRequest {

    private String mileStoneName;
    private LocalDate startDate;
    private LocalDate endDate;
}
