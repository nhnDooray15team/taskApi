package com.nhnacademy.taskApi.domain;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Entity
@Table(name = "milestones")
@NoArgsConstructor
public class Milestones {

    @EmbeddedId
    private Pk pk;

    @ManyToOne
    @MapsId("projectId")
    private Project project;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Getter
    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    static class Pk implements Serializable {

        @Column(name = "mile_stone_name")
        private String mileStoneName;

        @Column(name = "project_id")
        private int projectId;
    }
}
