package com.nhnacademy.taskApi.dto.milestone.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MilestonesModifyRequest {

    private String mileStoneName;
    private Date startDate;
    private Date endDate;
}
