package com.nhnacademy.taskApi.service.milestone;

<<<<<<< HEAD

import com.nhnacademy.taskApi.domain.Milestones;
import com.nhnacademy.taskApi.dto.milestone.request.MilestonesRequest;
=======
import com.nhnacademy.taskApi.dto.milestone.request.MilestonesCreateRequest;
import com.nhnacademy.taskApi.dto.milestone.request.MilestonesModifyRequest;
>>>>>>> dev-version-0.2
import com.nhnacademy.taskApi.dto.milestone.response.MilestonesResponse;
import com.nhnacademy.taskApi.repository.MilestonesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MileStoneService{

    private final MilestonesRepository milestonesRepository;



<<<<<<< HEAD
    public List<MilestonesResponse> getMileStones(Long projectId) {
        return milestonesRepository.findAllByProject_ProjectId(projectId);
    }

=======
    List<MilestonesResponse> getMileStones(Long projectId);

    void createMilestone(Long projectId, MilestonesCreateRequest milestonesRequest);

    void modifyMilestone(Long projectId, Long milestoneId, MilestonesModifyRequest milestonesModifyRequest);
>>>>>>> dev-version-0.2

    public void createMilestone(MilestonesRequest milestonesRequest) {
        // #TODO 1 :  프로젝트 만들고 해야할듯.
       Milestones milestones = new Milestones();
    }
}
