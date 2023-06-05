package com.nhnacademy.taskApi.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "projects")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Project {
    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "project_description")
    private String projectDescription;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private ProjectStatus statusId;

    @OneToMany(mappedBy = "project")
    private List<Authority> authorities;


    @OneToMany(mappedBy = "project")
    private List<Tag> tags;

    @OneToMany(mappedBy = "project")
    private List<Milestones> milestones;


    @OneToMany(mappedBy = "project")
    private List<Task> tasks;


}
