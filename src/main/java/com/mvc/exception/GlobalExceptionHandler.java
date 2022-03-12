package com.mvc.exception;


import com.mvc.dto.response.ErrResponse;
import org.apache.tomcat.websocket.AuthenticationException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {
    //when have exeption co the custom lai exception va return in json format to client
//Muon bat exeption nao thi ta cho no vao trong exceptionhandler
    @ExceptionHandler({BadRequesetException.class, ResourceNotFoundException.class, AuthenticationException.class})
    public ResponseEntity<ErrResponse> handle(Exception e) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                //ham
                .body(ErrResponse.error(HttpStatus.BAD_REQUEST.value(), e.getMessage()));


    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrResponse> handle1(MethodArgumentNotValidException e) {
        ObjectError error = e.getAllErrors().get(0);

        Map<String, String> errMap = new HashMap<>();
        List<FieldError> listerr = e.getBindingResult().getFieldErrors();
        for (FieldError err : listerr) {
            errMap.put(err.getField(), err.getDefaultMessage());
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                //ham
                .body(ErrResponse.error(HttpStatus.BAD_REQUEST.value(), errMap));


    }
}


