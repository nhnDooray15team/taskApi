package com.nhnacademy.taskApi.repository;


import com.nhnacademy.taskApi.domain.QTag;
import com.nhnacademy.taskApi.domain.QTaskTag;
import com.nhnacademy.taskApi.domain.Tag;
import com.nhnacademy.taskApi.dto.tag.response.QTagDto;
import com.nhnacademy.taskApi.dto.tag.response.TagDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;


public class TagRepositoryImpl extends QuerydslRepositorySupport implements TagRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public TagRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Tag.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public boolean existsByProjectIdAndTagName(Long projectId, String tagName) {
        QTag tag = QTag.tag;
        Integer result = jpaQueryFactory.selectOne()
                .from(tag)
                .where(tag.project.projectId.eq(projectId), tag.tagName.eq(tagName))
                .fetchFirst();
        return result!=null;
    }

    @Override
    public List<TagDto> findByTaskId(Long taskId) {
        QTag tag = QTag.tag;
        QTaskTag taskTag = QTaskTag.taskTag;

        return from(tag)
                .innerJoin(taskTag).fetchJoin()
                .on(taskTag.pk.tagId.eq(tag.tagId))
                .where(taskTag.pk.taskId.eq(taskId))
                .select(new QTagDto(tag.tagId, tag.tagName, taskTag.pk.taskId))
                .fetch();
    }
}
