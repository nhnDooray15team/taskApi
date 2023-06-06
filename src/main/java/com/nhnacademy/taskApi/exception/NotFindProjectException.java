package com.nhnacademy.taskApi.exception;

public class NotFindProjectException extends IllegalArgumentException {
    public NotFindProjectException(String content) {
        super(content);
    }
}
