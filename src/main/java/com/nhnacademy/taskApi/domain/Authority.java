package com.nhnacademy.taskApi.domain;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
public class Authority {
    @EmbeddedId
    private Pk pk;
    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;

    //나중에 Enum으로 추가
    @Enumerated(EnumType.STRING)
    private Role role;

    @Embeddable
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class Pk implements Serializable{

        private String userId;
        private Long projectId;
    }

    public enum Role{
        ADMIN, MEMBER
    }
}
