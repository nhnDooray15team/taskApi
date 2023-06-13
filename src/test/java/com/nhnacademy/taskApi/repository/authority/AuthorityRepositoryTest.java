package com.nhnacademy.taskApi.repository.authority;

import com.nhnacademy.taskApi.domain.Authority;
import com.nhnacademy.taskApi.domain.Project;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("AuthorityRepositoryTest")
class AuthorityRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AuthorityRepository authorityRepository;


    @Test
    @Order(1)
    @DisplayName("project id로 Authority list 테스트")
    void getAuthorityDtoByProjectId() {

    }
}