package com.nhnacademy.taskApi.domain;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Authority {

    @EmbeddedId
    private AuthorityId authority;
    @ManyToOne
    @MapsId("projectId")
    private Project project;

    private String role;

    @Embeddable
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public class AuthorityId implements Serializable{

        @Column(name = "user_id")
        private int userId;
        @Column(name = "project_id")
        private int projectId;

    }
}
