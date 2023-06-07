package com.nhnacademy.taskApi.exception;

public class NotFoundTaskException extends NullPointerException {
    public NotFoundTaskException(String content) {
        super(content);
    }
}
