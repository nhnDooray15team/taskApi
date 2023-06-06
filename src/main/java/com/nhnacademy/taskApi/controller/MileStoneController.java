package com.nhnacademy.taskApi.controller;

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


    @GetMapping("/{project_id}/milestones")
    @ResponseStatus(HttpStatus.OK)
    public List<MilestonesResponse> getAllmilestones(@PathVariable Integer project_id){

        return mileStoneService.getMileStones(project_id);
    }
}