package com.nhnacademy.taskApi.exception.project;

public class ProjectNotFoundList extends RuntimeException{

    public ProjectNotFoundList(String message) {
       super(message);
    }
}
