package com.nhnacademy.taskApi.service.milestone;

import com.nhnacademy.taskApi.domain.Milestones;
import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.dto.milestone.request.MilestonesModifyRequest;
import com.nhnacademy.taskApi.dto.milestone.request.MilestonesRequest;
import com.nhnacademy.taskApi.dto.milestone.response.MilestonesResponse;
import com.nhnacademy.taskApi.exception.DuplicatedException;
import com.nhnacademy.taskApi.exception.InvalidRequestException;
import com.nhnacademy.taskApi.exception.NotFoundException;
import com.nhnacademy.taskApi.repository.milestone.MilestonesRepository;
import com.nhnacademy.taskApi.repository.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class MileStoneService {

    private final MilestonesRepository milestonesRepository;

    private final ProjectRepository projectRepository;


    @Transactional(readOnly = true)
    public List<MilestonesResponse> getMileStones(Long projectId) {

        return milestonesRepository.findAllByProject_ProjectId(projectId)
                .get()
                .stream()
                .map(m -> new MilestonesResponse(
                        m.getMileStoneId(),
                        m.getProject().getProjectId(),
                        m.getMileStoneName(),
                        m.getStartDate(),
                        m.getEndDate()))
                .collect(Collectors.toList());
    }


    public void createMilestone(Long projectId, MilestonesRequest milestonesRequest) {
        if(projectRepository.existsById(projectId)){
            throw new DuplicatedException("이미 프로젝트가 존재합니다.");
        }

        final Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new NotFoundException("만든 프로젝트가 존재하지않습니다.")
        );

        milestonesRepository.save(new Milestones(project, milestonesRequest.getMileStoneName(),
                milestonesRequest.getStartDate(), milestonesRequest.getEndDate()));

    }


    public void modifyMilestone(Long projectId, Long milestoneId, MilestonesModifyRequest milestonesModifyRequest) {
        final Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new NotFoundException("프로젝트가 존재하지않습니다.")
        );

        final Milestones milestones = milestonesRepository.findById(milestoneId).orElseThrow(
                () -> new NotFoundException("마일스톤이 존재하지않습니다.")
        );

        milestones.setMileStoneName(milestonesModifyRequest.getMileStoneName());
        milestones.setStartDate(milestonesModifyRequest.getStartDate());
        milestones.setEndDate(milestonesModifyRequest.getEndDate());

        milestonesRepository.save(milestones);
    }

    public void deleteMilestone(Long milestoneId){

        if(!milestonesRepository.existsById(milestoneId)){
            throw new InvalidRequestException("마일스톤이 존재하지 않습니다.");
        }

        milestonesRepository.deleteById(milestoneId);
    }
}
