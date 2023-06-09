package com.nhnacademy.taskApi.domain;


import com.querydsl.core.annotations.QueryEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "projects")
@NoArgsConstructor
@Getter
@NoArgsConstructor
public class Project {
    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "project_description")
    private String projectDescription;

    @ManyToOne
    @JoinColumn(name = "status_id")
    @Setter
    private ProjectStatus projectStatus;

    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
    private List<Authority> authorities;

    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
    private List<Tag> tags;

    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
    private List<Milestones> milestones;


    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
    private List<Task> tasks;


<<<<<<< HEAD
    public Project(Long projectId, String projectName, String projectDescription, ProjectStatus statusId) {
=======
    public Project(Long projectId, String projectName, String projectDescription, ProjectStatus projectStatus) {
>>>>>>> dev-version-0.2
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectStatus = projectStatus;
    }


}
