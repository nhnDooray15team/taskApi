package com.nhnacademy.taskApi.dto.milestone.request;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class MilestonesRequest {
    //등록부분에 project_id를 등록하는것이 있어 이부분 이야기 필요함
    private Long projectId;
    private String mileStoneName;
    private LocalDate startDate;
    private LocalDate endDate;
}
