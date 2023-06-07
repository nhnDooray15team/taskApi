package com.nhnacademy.taskApi.service.milestone;

import com.nhnacademy.taskApi.dto.milestone.request.MilestonesCreateRequest;
import com.nhnacademy.taskApi.dto.milestone.request.MilestonesModifyRequest;
import com.nhnacademy.taskApi.dto.milestone.response.MilestonesResponse;
import java.util.List;



public interface MileStoneService {

    List<MilestonesResponse> getMileStones(Long projectId);

    void createMilestone(Long projectId, MilestonesCreateRequest milestonesRequest);

    void modifyMilestone(Long projectId, Long milestoneId, MilestonesModifyRequest milestonesModifyRequest);

}
