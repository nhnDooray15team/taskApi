package com.nhnacademy.taskApi.repository.comment;


import com.nhnacademy.taskApi.domain.*;
import com.nhnacademy.taskApi.dto.comment.response.CommentResponseDto;
import com.nhnacademy.taskApi.dto.comment.response.QCommentResponseDto;
//import com.nhnacademy.taskApi.dto.tag.response.QTagDto;
//import com.nhnacademy.taskApi.dto.tag.response.TagDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;


public class CommentRepositoryImpl extends QuerydslRepositorySupport implements CommentRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;


    public CommentRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Tag.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
    @Override
    public List<CommentResponseDto> findByTaskId(Long taskId) {
        QComment comment = QComment.comment;
        QTask task = QTask.task;

        return from(comment)
                .innerJoin(task).fetchJoin()
                .on(task.taskId.eq(comment.task.taskId))
                .select(new QCommentResponseDto(comment.commentsId,
                        comment.writerId, comment.content, comment.registerDate))
                .fetch();
    }
}
