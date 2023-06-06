package com.nhnacademy.taskApi.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Integer taskId;
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "task_name")
    private String taskName;

    private String content;

    @Column(name = "register_date")
    private LocalDateTime registerDate;
<<<<<<< HEAD

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

//    @ManyToOne
//    @JoinColumn(name = "mile_stone_name")
//    @JoinColumn(name = "project_id")
//    private Milestones milestones;
=======
    @Column(name = "end_date")
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "mile_stone_id")
    private Milestones milestones;
>>>>>>> 5186feb918042d5ac02872a4a6b44b432c46ab67

    @OneToMany(mappedBy = "task")
    private List<Comment> comments;

    @OneToMany(mappedBy = "task")
    private List<TaskTag> tags;

}
