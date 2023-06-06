package com.nhnacademy.taskApi.domain;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Table(name = "authorities")
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @EmbeddedId
    private Pk pk;

    @ManyToOne
    @MapsId("projectId")
    private Project project;

    private String role;

    @Getter
    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class Pk implements Serializable {
        @Column(name = "user_id")
        private Long userId;

        @Column(name = "project_id")
        private Long projectId;

    }
}
