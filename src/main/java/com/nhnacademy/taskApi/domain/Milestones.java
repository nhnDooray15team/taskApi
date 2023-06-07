package com.nhnacademy.taskApi.domain;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "milestones")
@NoArgsConstructor
@Getter
public class Milestones {
    @Id
    @Column(name = "mile_stone_id")
    private Long mileStoneId;

    @Setter
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Setter
    @Column(name = "mile_stone_name")
    private String mileStoneName;

    @Setter
    @Column(name = "start_date")
    private LocalDate startDate;


    @Setter
    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToMany(mappedBy = "milestones")
    private List<Task> tasks;


    public Milestones(Project project, String mileStoneName, LocalDate startDate, LocalDate endDate) {
        this.project = project;
        this.mileStoneName = mileStoneName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
