package com.nhnacademy.taskApi.repository.project;

import com.nhnacademy.taskApi.domain.QAuthority;
import com.nhnacademy.taskApi.domain.QProject;
import com.nhnacademy.taskApi.dto.project.response.ProjectGetResponse;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import java.util.List;

public class ProjectRepositoryImpl extends QuerydslRepositorySupport implements ProjectRepostioryCustom {
    public ProjectRepositoryImpl(Class<?> domainClass) {
        super(domainClass);
    }

    @Override
    public List<ProjectGetResponse> getAllProjectBy(String userid) {
        QProject project = QProject.project;
        QAuthority authority = QAuthority.authority;
        return from(project)
                .join(authority)
                .on(project.projectId.eq(authority.pk.projectId))
                .where(authority.pk.userId.eq(userid))
                .select(Projections.constructor(ProjectGetResponse.class,
                        project.projectId,
                        project.projectName,
                        project.projectStatus.statusId,
                        authority.role))
                .fetch();
    }
}
