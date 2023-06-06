package com.nhnacademy.taskApi.domain;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tags")
@Getter
public class Tag {

    @EmbeddedId
    private Pk pk;

    @ManyToOne
    @MapsId("projectId")
    private Project project;

    @OneToMany(mappedBy = "tag")
    private List<TaskTag> tasks;


    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    static class Pk implements Serializable {

        @Column(name = "tag_name")
        private String tagName;

        @Column(name = "project_id")
        private int projectId;


    }
}
