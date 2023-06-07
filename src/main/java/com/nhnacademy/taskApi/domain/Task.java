package com.nhnacademy.taskApi.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tasks")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    @Setter
    @Column(name = "task_name")
    private String taskName;

    @Setter
    private String content;

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mile_stone_id")
    private Milestones milestones;
//
    @OneToMany(mappedBy = "task")
    private List<Comment> commentList;
//
    @OneToMany(mappedBy = "task")
    private List<TaskTag> tags;


    public Task(String taskName, String content, LocalDateTime registerDate, LocalDateTime endDate, Project project, Milestones milestones) {
        this.taskName = taskName;
        this.content = content;
        this.registerDate = registerDate;
        this.endDate = endDate;
        this.project = project;
        this.milestones = milestones;
    }
}
