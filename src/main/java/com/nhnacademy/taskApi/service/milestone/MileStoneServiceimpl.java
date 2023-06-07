package com.nhnacademy.taskApi.service.milestone;

import com.nhnacademy.taskApi.domain.Milestones;
import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.dto.milestone.request.MilestonesCreateRequest;
import com.nhnacademy.taskApi.dto.milestone.request.MilestonesModifyRequest;
import com.nhnacademy.taskApi.dto.milestone.response.MilestonesResponse;
import com.nhnacademy.taskApi.repository.milestone.MilestonesRepository;
import com.nhnacademy.taskApi.repository.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MileStoneServiceimpl implements MileStoneService {

    private final MilestonesRepository milestonesRepository;

    private final ProjectRepository projectRepository;


    @Override
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

    @Override
    public void createMilestone(Long projectId, MilestonesCreateRequest milestonesRequest) {
        Project project = projectRepository.findById(projectId).get();

        milestonesRepository.save(new Milestones(project, milestonesRequest.getMileStoneName(),
                milestonesRequest.getStartDate(), milestonesRequest.getEndDate()));

    }

    @Override
    public void modifyMilestone(Long projectId, Long milestoneId ,MilestonesModifyRequest milestonesModifyRequest) {
        Project project = projectRepository.findById(projectId).get();

        Milestones milestones = milestonesRepository.findById(milestoneId).get();

        milestones.setMileStoneName(milestonesModifyRequest.getMileStoneName());
        milestones.setStartDate(milestonesModifyRequest.getStartDate());
        milestones.setEndDate(milestonesModifyRequest.getEndDate());

        milestonesRepository.save(milestones);
    }
}
