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
<<<<<<< HEAD

    @EmbeddedId
    private Pk pk;
=======
    @Id
    @Column(name = "mile_stone_id")
    private Integer mileStoneId;
>>>>>>> 5186feb918042d5ac02872a4a6b44b432c46ab67

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

<<<<<<< HEAD
=======
    @Column(name = "mile_stone_name")
    private String mileStoneName;
>>>>>>> 5186feb918042d5ac02872a4a6b44b432c46ab67
    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

<<<<<<< HEAD
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
=======
    @OneToMany
    private List<Task> tasks;


>>>>>>> 5186feb918042d5ac02872a4a6b44b432c46ab67
}
