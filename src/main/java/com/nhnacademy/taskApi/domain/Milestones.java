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
    @Id
    @Column(name = "mile_stone_id")
    private Integer mileStoneId;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "mile_stone_name")
    private String mileStoneName;
    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @OneToMany
    private List<Task> tasks;


}
