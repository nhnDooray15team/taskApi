package com.nhnacademy.taskApi.domain;

<<<<<<< HEAD
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
=======

import lombok.*;
import org.hibernate.cfg.PkDrivenByDefaultMapsIdSecondPass;
>>>>>>> 5186feb918042d5ac02872a4a6b44b432c46ab67

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tasks_has_tags")
public class TaskTag {
<<<<<<< HEAD

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
=======
    @EmbeddedId
    private Pk pk;
    @MapsId("taskId")
>>>>>>> 5186feb918042d5ac02872a4a6b44b432c46ab67
    @JoinColumn(name = "task_id")
    @ManyToOne
    private Task task;

<<<<<<< HEAD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_name")
    @JoinColumn(name = "project_id")
    private Tag tag;

=======
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
>>>>>>> 5186feb918042d5ac02872a4a6b44b432c46ab67
}
