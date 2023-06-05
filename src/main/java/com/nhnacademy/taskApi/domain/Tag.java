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


    @ManyToOne
    @MapsId("projectId")
    private Project project;

    @EmbeddedId
    private TagId tag;

    @OneToMany(mappedBy = "tag")
    private List<Task> tasks;

    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public class TagId implements Serializable{

        @Column(name = "tag_name")
        private String tag_name;

        @Column(name = "project_id")
        private int projectId;
    }
}
