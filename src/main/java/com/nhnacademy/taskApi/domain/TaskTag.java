package com.nhnacademy.taskApi.domain;


import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks_has_tags")
public class TaskTag {
    @EmbeddedId
    private Pk pk;
    @MapsId("taskId")
    @JoinColumn(name = "task_id")
    @ManyToOne
    private Task task;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    @MapsId("tagId")
    private Tag tag;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class Pk implements Serializable{
        private Long taskId;
        private Long tagId;
    }
}
