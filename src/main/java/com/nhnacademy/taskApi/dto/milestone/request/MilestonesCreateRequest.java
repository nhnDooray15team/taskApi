package com.nhnacademy.taskApi.dto.milestone.request;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
<<<<<<< HEAD:src/main/java/com/nhnacademy/taskApi/dto/milestone/request/MilestonesRequest.java
public class MilestonesRequest {
    //등록부분에 project_id를 등록하는것이 있어 이부분 이야기 필요함
    private Long projectId;
=======
public class MilestonesCreateRequest {

    private Long mileStoneId;
>>>>>>> dev-version-0.2:src/main/java/com/nhnacademy/taskApi/dto/milestone/request/MilestonesCreateRequest.java
    private String mileStoneName;
    private LocalDate startDate;
    private LocalDate endDate;
}
