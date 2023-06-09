package com.nhnacademy.taskApi.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project_status")
@NoArgsConstructor
@Getter
public class ProjectStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "status_name")
    @Enumerated(EnumType.STRING)
    private StatusName statusName;

    @OneToMany
    private List<Project> projects;

    public enum StatusName{
        ACTIVATED, DORMANT, ENDED;
    }
}
