package com.nhnacademy.taskApi.controller;


import com.nhnacademy.taskApi.dto.milestone.request.MilestonesModifyRequest;
import com.nhnacademy.taskApi.dto.milestone.request.MilestonesRequest;
import com.nhnacademy.taskApi.dto.milestone.response.MilestonesResponse;
import com.nhnacademy.taskApi.service.milestone.MileStoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class MileStoneController {

    private final MileStoneService mileStoneService;


    @GetMapping("/{projectId}/milestones")
    @ResponseStatus(HttpStatus.OK)
    public List<MilestonesResponse> getAllmilestones(@PathVariable("project_id") Long projectId){
        return mileStoneService.getMileStones(projectId);
    }

    @PostMapping("/{project_id}/milestone")
    @ResponseStatus(HttpStatus.CREATED)
    public void createMilestone(@PathVariable("project_id") Long projectId,
                                @RequestBody MilestonesRequest milestonesRequest){
        mileStoneService.createMilestone(projectId, milestonesRequest);

    }

    @PutMapping("/{project_id}/milestones/{milestones_id}")
    public void modifyMilestone(@PathVariable("project_id") Long projectId, @PathVariable("milestones_id") Long milestoneId,
                                @RequestBody MilestonesModifyRequest milestonesModifyRequest){

        mileStoneService.modifyMilestone(projectId,milestoneId,milestonesModifyRequest);

    }

    @DeleteMapping("/{project_id}/mildstones/{milestones_id}")
    public void deleteMilestone(@PathVariable("milestones_id") Long milestoneId ){
        mileStoneService.deleteMilestone(milestoneId);

    }

}
