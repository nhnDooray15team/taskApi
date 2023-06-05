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
    private AuthorityPk authorityPk;
    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;

    //나중에 Enum으로 추가
    private String role;

    @Embeddable
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static class AuthorityPk implements Serializable{
        private Integer userId;
        private Integer projectId;
    }

}
