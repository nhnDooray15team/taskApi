package com.nhnacademy.taskApi.advice;

import com.nhnacademy.taskApi.exception.DuplicatedException;
import com.nhnacademy.taskApi.exception.InvalidRequestException;
import com.nhnacademy.taskApi.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity runNotfound(NotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND) ;
    }

    @ExceptionHandler(value = DuplicatedException.class)
    public ResponseEntity runDuplicated(DuplicatedException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST) ;
    }

    @ExceptionHandler(value = InvalidRequestException.class)
    public ResponseEntity runInvalid(InvalidRequestException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST) ;
    }


    /***
     *
     * @param ex MethodArgumentNotValidException의 에러 메세지 출력값
     * @return @Valid에서 에러 발생시 각 지정해놓은 메세지 출력
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity validationException(MethodArgumentNotValidException ex){
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .findFirst()
                .orElse("validationException 에서 오류를 확인해보세요.");
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST) ;
    }
}
