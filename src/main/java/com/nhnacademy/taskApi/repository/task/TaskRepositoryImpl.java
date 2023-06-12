package com.nhnacademy.taskApi.repository.task;


import com.nhnacademy.taskApi.domain.*;
import com.nhnacademy.taskApi.dto.comment.response.CommentResponseDto;
import com.nhnacademy.taskApi.dto.comment.response.QCommentResponseDto;
import com.nhnacademy.taskApi.dto.tag.response.QTagDto;
import com.nhnacademy.taskApi.dto.tag.response.TagDto;
import com.nhnacademy.taskApi.repository.tag.TagRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;


public class TaskRepositoryImpl extends QuerydslRepositorySupport implements TaskRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;


    public TaskRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Tag.class);
        this.jpaQueryFactory = jpaQueryFactory;
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
