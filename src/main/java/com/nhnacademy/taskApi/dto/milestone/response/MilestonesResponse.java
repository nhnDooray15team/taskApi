package com.nhnacademy.taskApi.dto.milestone.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class MilestonesResponse {

    private Long milestoneId;
    private Long projectId;
    private String mileStoneName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
