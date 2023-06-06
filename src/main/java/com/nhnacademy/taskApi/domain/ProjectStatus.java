package com.nhnacademy.taskApi.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "project_status")
@NoArgsConstructor
public class ProjectStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private int statusId;

    @Column(name = "status_name")
    private String statusName;

    @OneToMany
    private List<Project> projects;
<<<<<<< HEAD

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
=======
>>>>>>> 5186feb918042d5ac02872a4a6b44b432c46ab67
}
