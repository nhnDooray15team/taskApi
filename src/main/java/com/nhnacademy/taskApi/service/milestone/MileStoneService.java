package com.nhnacademy.taskApi.service.milestone;


import com.nhnacademy.taskApi.domain.Milestones;
import com.nhnacademy.taskApi.dto.milestone.request.MilestonesRequest;
import com.nhnacademy.taskApi.dto.milestone.response.MilestonesResponse;
import com.nhnacademy.taskApi.repository.MilestonesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MileStoneService{

    private final MilestonesRepository milestonesRepository;



    public List<MilestonesResponse> getMileStones(Long projectId) {
        return milestonesRepository.findAllByProject_ProjectId(projectId);
    }


    public void createMilestone(MilestonesRequest milestonesRequest) {
        // #TODO 1 :  프로젝트 만들고 해야할듯.
       Milestones milestones = new Milestones();
    }
}
