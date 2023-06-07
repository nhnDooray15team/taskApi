package com.nhnacademy.taskApi.exception;

public class NotFoundProjectStatus extends IllegalArgumentException {
    public NotFoundProjectStatus(String s) {
        super(s);
    }
}
