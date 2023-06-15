package com.nhnacademy.taskApi.controller;


import com.nhnacademy.taskApi.dto.milestone.request.MilestonesModifyRequest;
import com.nhnacademy.taskApi.dto.milestone.request.MilestonesRequest;
import com.nhnacademy.taskApi.dto.milestone.response.MilestonesResponse;
import com.nhnacademy.taskApi.service.milestone.MileStoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;



@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class MileStoneController {

    private final MileStoneService mileStoneService;


    @GetMapping("/{projectId}/milestones")
    public List<MilestonesResponse> getAllMilestones(@PathVariable("projectId") Long projectId){
        return mileStoneService.getMileStones(projectId);
    }

    @PostMapping("/{projectId}/milestone")
    @ResponseStatus(HttpStatus.CREATED)
    public void createMilestone(@PathVariable("projectId") Long projectId,
                                @RequestBody @Valid MilestonesRequest milestonesRequest){

        mileStoneService.createMilestone(projectId, milestonesRequest);

    }

    @PatchMapping("/{projectId}/milestones/{milestonesId}")
    @ResponseStatus(HttpStatus.OK)
    public void modifyMilestone(@PathVariable("projectId") Long projectId,
                                @PathVariable("milestonesId") Long milestoneId,
                                @RequestBody @Valid MilestonesModifyRequest milestonesModifyRequest){



        mileStoneService.modifyMilestone(projectId,milestoneId,milestonesModifyRequest);

    }

    @DeleteMapping("/{projectId}/milestones/{milestonesId}")
    public void deleteMilestone(@PathVariable("milestonesId") Long milestoneId ){
        mileStoneService.deleteMilestone(milestoneId);

    }

}
