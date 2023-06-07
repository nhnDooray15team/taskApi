package com.nhnacademy.taskApi.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tags")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tag{
    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Setter
    @Column(name = "tag_name")
    private String tagName;

    @Setter
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.REMOVE)
    private List<TaskTag> tagList;
    public Tag(String tagName, Project project){
        this.tagName =tagName;
        this.project=project;
    }
}