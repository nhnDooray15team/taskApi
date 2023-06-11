package com.nhnacademy.taskApi.repository;

import com.nhnacademy.taskApi.repository.project.ProjectRepository;
import com.nhnacademy.taskApi.repository.tag.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TagRepository tagRepository;

    @Test
    void findById(){
//        projectRepository.findAll()
//                .forEach(System.out::println);
        tagRepository.findAll()
                .forEach(System.out::println);
    }



}