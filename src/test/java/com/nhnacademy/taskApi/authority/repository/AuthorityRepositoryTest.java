package com.nhnacademy.taskApi.authority.repository;

import com.nhnacademy.taskApi.config.testConfig;
import com.nhnacademy.taskApi.domain.Authority;
import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.domain.ProjectStatus;
import com.nhnacademy.taskApi.dto.authority.response.AuthorityDto;
import com.nhnacademy.taskApi.repository.authority.AuthorityRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("AuthorityRepositoryTest")
@Import(testConfig.class)
class AuthorityRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AuthorityRepository authorityRepository;

    private ProjectStatus projectStatus1;
    private Project project1;
    private Authority authority1;
    private Authority authority2;

    @BeforeEach
    void setUp() {

        projectStatus1 = new ProjectStatus(ProjectStatus.StatusName.ACTIVATED);
        project1 = new Project("Java-백엔드-3기-교육", "Java백엔드 교육 관련 업무 등록 예정", projectStatus1);
        authority1 = new Authority(new Authority.Pk("user1", project1.getProjectId()), project1, Authority.Role.ADMIN);
        authority2 = new Authority(new Authority.Pk("user2", project1.getProjectId()), project1, Authority.Role.MEMBER);

        testEntityManager.persist(projectStatus1);
        testEntityManager.persist(project1);
        testEntityManager.persist(authority1);
        testEntityManager.persist(authority2);

    }

    @Test
    @DisplayName("project id로 Authority list 테스트")
    void getAuthorityDtoByProjectId() {
        List<AuthorityDto> authorityDtoList = authorityRepository.getAuthorityDtoByProjectId(project1.getProjectId());

        assertThat(authorityDtoList).hasSize(2);

        assertThat(authorityDtoList.get(0).getUserId().equals(authority1.getPk().getUserId()) && authorityDtoList.get(0).getRole().equals(authority1.getRole()));
        assertThat(authorityDtoList.get(1).getUserId().equals(authority1.getPk().getUserId()) && authorityDtoList.get(1).getRole().equals(authority1.getRole()));



    }

    @Test
    @DisplayName("project id가진 authority의 존재 테스트")
    void existsByProjectId() {
        ProjectStatus projectStatus2 = new ProjectStatus(ProjectStatus.StatusName.DORMANT);
        Project project3 = new Project("Mini_Dooray_15Team", "MiniDooray 팀프로젝트 회의 및 규칙사항 작성", projectStatus2);

        testEntityManager.persist(project3);
        testEntityManager.persist(projectStatus2);

        assertThat(authorityRepository.existsByProjectId(project1.getProjectId())).isEqualTo(true);
        assertThat(authorityRepository.existsByProjectId(project3.getProjectId())).isEqualTo(false);


    }
}
