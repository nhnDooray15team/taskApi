package com.nhnacademy.taskApi.dto.milestone.response;


import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Getter
@Setter
public class MilestonesResponse {
    private Integer mileStoneId;
    private Integer projectId;
    private String mileStoneName;
    private Date startDate;
    private Date endDate;
}
