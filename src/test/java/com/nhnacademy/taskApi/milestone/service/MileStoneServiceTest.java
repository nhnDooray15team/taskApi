package com.nhnacademy.taskApi.milestone.service;

import com.nhnacademy.taskApi.domain.Milestones;
import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.dto.milestone.request.MilestonesModifyRequest;
import com.nhnacademy.taskApi.dto.milestone.request.MilestonesRequest;
import com.nhnacademy.taskApi.dto.milestone.response.MilestonesResponse;
import com.nhnacademy.taskApi.exception.DuplicatedException;
import com.nhnacademy.taskApi.exception.NotFoundException;
import com.nhnacademy.taskApi.repository.milestone.MilestonesRepository;
import com.nhnacademy.taskApi.repository.project.ProjectRepository;
import com.nhnacademy.taskApi.service.milestone.MileStoneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;


@ExtendWith(MockitoExtension.class)
class MileStoneServiceTest {

    @Mock
    private MilestonesRepository milestonesRepository;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private MileStoneService mileStoneService;

    @Test
    @DisplayName("프로젝트 ID에 속한 마일스톤들 조회")
    void getMileStones() {
        Long projectId = 1L;
        List<Milestones> milestones = new ArrayList<>();

        Mockito.when(milestonesRepository.findAllByProject_ProjectId(projectId)).thenReturn(Optional.of(milestones));
        List<MilestonesResponse> result = mileStoneService.getMileStones(projectId);

        assertEquals(milestones, result);

        Mockito.verify(milestonesRepository, Mockito.times(1)).findAllByProject_ProjectId(projectId);
        Mockito.verifyNoMoreInteractions(milestonesRepository);
    }

    @Test
    @DisplayName("해당 프로젝트 ID에 마일스톤 등록")
    void createMilestone() {
        // Given
        Long projectId = 1L;
        MilestonesRequest milestonesRequest = new MilestonesRequest();

        ReflectionTestUtils.setField(milestonesRequest,"mileStoneName", "마일스톤테스트");
        ReflectionTestUtils.setField(milestonesRequest,"startDate", LocalDateTime.now());
        ReflectionTestUtils.setField(milestonesRequest,"endDate", LocalDateTime.now());

        Project project = new Project();
        Mockito.when(projectRepository.existsById(projectId))
                .thenReturn(false);
        Mockito.when(projectRepository.findById(projectId))
                .thenReturn(Optional.of(project));


        // Then
        mileStoneService.createMilestone(projectId, milestonesRequest);

        ArgumentCaptor<Milestones> captor = ArgumentCaptor.forClass(Milestones.class);
        Mockito.verify(milestonesRepository, Mockito.times(1)).save(captor.capture());

        Milestones savedMilestone = captor.getValue();

        assertEquals(milestonesRequest.getMilestoneId(),savedMilestone.getMileStoneId() );
        assertEquals(milestonesRequest.getMileStoneName(),savedMilestone.getMileStoneName() );
        assertEquals(milestonesRequest.getStartDate(),savedMilestone.getStartDate() );
        assertEquals(milestonesRequest.getEndDate(),savedMilestone.getEndDate() );


        Mockito.verify(projectRepository, Mockito.times(1)).existsById(projectId);
        Mockito.verify(projectRepository, Mockito.times(1)).findById(projectId);
        Mockito.verifyNoMoreInteractions(projectRepository);

    }

    @Test
    @DisplayName("해당 프로젝트 ID에 마일스톤 등록 - 프로젝트존재함")
    void createMilestoneException() {

        Long projectId = 1L;
        MilestonesRequest milestonesRequest = new MilestonesRequest();
        ReflectionTestUtils.setField(milestonesRequest,"milestoneId", 1L);
        ReflectionTestUtils.setField(milestonesRequest,"mileStoneName", "마일스톤테스트");
        ReflectionTestUtils.setField(milestonesRequest,"startDate", LocalDateTime.now());
        ReflectionTestUtils.setField(milestonesRequest,"endDate", LocalDateTime.now());

        Mockito.when(projectRepository.existsById(projectId))
                .thenReturn(true);


        assertThrows(DuplicatedException.class, () -> mileStoneService.createMilestone(projectId, milestonesRequest));
        // Then
        Mockito.verifyNoMoreInteractions(projectRepository);


    }

    @Test
    @DisplayName("해당 프로젝트 ID에 마일스톤 등록 - 마일스톤 존재하지않음")
    void createMilestoneException2() {

        Long projectId = 1L;
        MilestonesRequest milestonesRequest = new MilestonesRequest();
        ReflectionTestUtils.setField(milestonesRequest,"milestoneId", 1L);
        ReflectionTestUtils.setField(milestonesRequest,"mileStoneName", "마일스톤테스트");
        ReflectionTestUtils.setField(milestonesRequest,"startDate", LocalDateTime.now());
        ReflectionTestUtils.setField(milestonesRequest,"endDate", LocalDateTime.now());

        assertThrows(NotFoundException.class, () -> mileStoneService.createMilestone(projectId, milestonesRequest));

    }


    @Test
    @DisplayName("해당 프로젝트 ID에 있는 마일스톤 ID 확인후 수정")
    void modifyMilestone() {
        Long projectId = 1L;
        Long milestoneId = 2L;
        MilestonesModifyRequest milestonesModifyRequest = new MilestonesModifyRequest();

        ReflectionTestUtils.setField(milestonesModifyRequest,"mileStoneName", "마일스톤테스트");
        ReflectionTestUtils.setField(milestonesModifyRequest,"startDate", LocalDateTime.now());
        ReflectionTestUtils.setField(milestonesModifyRequest,"endDate", LocalDateTime.now());

        Project project = new Project();
        Milestones milestones = new Milestones();

        Mockito.when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        Mockito.when(milestonesRepository.findById(milestoneId)).thenReturn(Optional.of(milestones));

        mileStoneService.modifyMilestone(projectId,milestoneId,milestonesModifyRequest);

        assertEquals(milestones.getMileStoneName(), milestonesModifyRequest.getMileStoneName());
        assertEquals(milestones.getStartDate(), milestonesModifyRequest.getStartDate());
        assertEquals(milestones.getEndDate(), milestonesModifyRequest.getEndDate());



    }

    @Test
    void deleteMilestone() {
    }
}