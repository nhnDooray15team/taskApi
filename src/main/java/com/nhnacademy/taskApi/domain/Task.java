package com.nhnacademy.taskApi.domain;


import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tasks")
@Getter
public class Task {

    @Id
    @Column(name = "task_id")
    private int taskId;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    private String content;

    @Column(name = "register_date")
    private LocalDateTime registerDate;


    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    private Milestones milestones;

    @OneToMany(mappedBy = "task")
    private List<Comment> comments;

    @OneToMany(mappedBy = "task")
    private List<Tag> tags;

}
