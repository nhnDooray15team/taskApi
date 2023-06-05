package com.nhnacademy.taskApi.domain;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
//
//@Entity
//@Table(name = "tags")
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Tag {
//    @EmbeddedId
//    private TagPk tag;
//
//    @ManyToOne
//    @MapsId("projectId")
//    @JoinColumn(name = "project_id")
//    private Project project;
//
//    @OneToMany(mappedBy = "tag")
//    private List<Task> tasks;
//
//    @Embeddable
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @EqualsAndHashCode
//    @Getter
//    public static class TagPk implements Serializable{
//
//        @Setter
//        @Column(name = "tag_name")
//        private String tagName;
//
//        private int projectId;
//    }
//}

@Entity
@Table(name = "tags")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Tag{
    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tagId;

    @Column(name = "tag_name")
    private String tagName;

    @ManyToOne()
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "tag")
    private List<TaskTag> tag;
}