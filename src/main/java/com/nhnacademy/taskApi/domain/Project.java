package com.nhnacademy.taskApi.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "projects")
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

    public Project(Long projectId, String projectName, String projectDescription,
                   ProjectStatus statusId) {

        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectStatus = projectStatus;
    }

}
