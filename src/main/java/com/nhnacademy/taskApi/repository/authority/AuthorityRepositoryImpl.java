package com.nhnacademy.taskApi.repository.authority;

import com.nhnacademy.taskApi.domain.Authority;
import com.nhnacademy.taskApi.domain.QAuthority;
import com.nhnacademy.taskApi.domain.QTag;
import com.nhnacademy.taskApi.dto.authority.response.AuthorityDto;
import com.nhnacademy.taskApi.dto.authority.response.QAuthorityDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class AuthorityRepositoryImpl extends QuerydslRepositorySupport implements AuthorityRepositoryCustom {

    private  JPAQueryFactory jpaQueryFactory;
    public AuthorityRepositoryImpl(JPAQueryFactory jpaQueryFactory){
        super(Authority.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }


    @Override
    public List<AuthorityDto> getAuthorityDtoByProjectId(Long projectId) {
        QAuthority authority= QAuthority.authority;
        return from(authority)
                .where(authority.pk.projectId.eq(projectId))
                .select(new QAuthorityDto(authority.pk.userId, authority.role))
                .fetch();
    }

    @Override
    public boolean existsByProjectId(Long projectId) {
        QAuthority authority = QAuthority.authority;
        Integer result = jpaQueryFactory.selectOne()
                .from(authority)
                .where(authority.pk.projectId.eq(projectId))
                .fetchFirst();
        return result!=null;
    }
}
