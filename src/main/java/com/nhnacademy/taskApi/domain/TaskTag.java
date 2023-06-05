package com.nhnacademy.taskApi.domain;


import lombok.*;
import org.hibernate.cfg.PkDrivenByDefaultMapsIdSecondPass;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tasks_has_tags")
public class TaskTag {
    @EmbeddedId
    private Pk pk;
    @MapsId("taskId")
    @JoinColumn(name = "task_id")
    @ManyToOne
    private Task task;

    @ManyToOne
    @JoinColumn(name = "tagId")
    @MapsId("tagId")
    private Tag tag;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable{
        private Integer taskId;
        private Integer tagId;
    }
}
