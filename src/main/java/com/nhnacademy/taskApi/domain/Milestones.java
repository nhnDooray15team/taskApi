package com.nhnacademy.taskApi.domain;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "milestones")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Milestones {

    @EmbeddedId
    private MilestonesId milestones;

    @ManyToOne
    @MapsId("projectId")
    private Project project;
    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @OneToMany
    @JoinColumn(name = "task_id")
    private List<Task> tasks;

    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public class MilestonesId implements Serializable {

        @Column(name = "mile_stone_name")
        private String mileStoneName;

        @Column(name = "project_id")
        private int projectId;
    }


}
