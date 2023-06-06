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
<<<<<<< HEAD
    private Pk pk;

=======
    private AuthorityPk authorityPk;
>>>>>>> 5186feb918042d5ac02872a4a6b44b432c46ab67
    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;

    //나중에 Enum으로 추가
    private String role;

    @Getter
    @Embeddable
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
<<<<<<< HEAD
    public static class Pk implements Serializable {
        @Column(name = "user_id")
        private Long userId;

        @Column(name = "project_id")
        private Long projectId;

=======
    public static class AuthorityPk implements Serializable{
        private Integer userId;
        private Integer projectId;
>>>>>>> 5186feb918042d5ac02872a4a6b44b432c46ab67
    }

}
