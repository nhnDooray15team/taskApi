package com.nhnacademy.taskApi.exception;

public class NotFoundProjectException extends IllegalArgumentException {
    public NotFoundProjectException(String content) {
        super(content);
    }
}
