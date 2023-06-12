package com.nhnacademy.taskApi.repository.project;

import com.nhnacademy.taskApi.domain.Project;
import com.nhnacademy.taskApi.domain.QAuthority;
import com.nhnacademy.taskApi.domain.QProject;
import com.nhnacademy.taskApi.domain.QProjectStatus;
import com.nhnacademy.taskApi.dto.project.response.ProjectGetResponse;
import com.nhnacademy.taskApi.dto.project.response.QProjectGetResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import java.util.List;

public class ProjectRepositoryImpl extends QuerydslRepositorySupport implements ProjectRepositoryCustom {
    private  final JPAQueryFactory jpaQueryFactory;
    public ProjectRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Project.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<ProjectGetResponse> getProjectsByUserId(Pageable pageable, String id) {
        QProject project = QProject.project;
        QAuthority authority = QAuthority.authority;
        QProjectStatus status = QProjectStatus.projectStatus;

        Long count = from(project)
                .innerJoin(authority).on(authority.pk.projectId.eq(project.projectId))
                .where(authority.pk.userId.eq(id))
                .select(project.count())
                .fetchOne();

        List<ProjectGetResponse> projectGetResponseList =  from(project)
                .innerJoin(authority).on(authority.pk.projectId.eq(project.projectId))
                .innerJoin(status).on(status.statusId.eq(project.projectStatus.statusId))
                .where(authority.pk.userId.eq(id))
                .select(new QProjectGetResponse(project.projectId,
                        project.projectName,
                        authority.role,
                        project.projectStatus.statusName))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();
        return new PageImpl<>(projectGetResponseList, pageable, count);
    }

    @Override
    public boolean existsProjectByUserId(String userId, String projectName) {
        QProject project = QProject.project;
        QAuthority authority = QAuthority.authority;

        Integer result = jpaQueryFactory.selectOne()
                .from(project)
                .innerJoin(authority).on(authority.pk.projectId.eq(project.projectId))
                .where(authority.pk.userId.eq(userId))
                .fetchFirst();
        return result != null;
    }
}
