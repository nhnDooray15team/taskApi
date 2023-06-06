package com.nhnacademy.taskApi.service.milestone;

import com.nhnacademy.taskApi.dto.milestone.request.MilestonesRequest;
import com.nhnacademy.taskApi.dto.milestone.response.MilestonesResponse;
import java.util.List;



public interface MileStoneService {

    List<MilestonesResponse> getMileStones(Integer projectId);

    void createMilestone(MilestonesRequest milestonesRequest);

}
