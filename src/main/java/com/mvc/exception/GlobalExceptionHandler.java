package com.mvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {
    //when have exeption co the custom lai exception va return in json format to client
//Muon bat exeption nao thi ta cho no vao trong exceptionhandler
    @ExceptionHandler({BadRequesetException.class, ResourceNotFoundException.class})
    public ResponseEntity<?> handle(Exception e) {
        Map<String, String> error = new HashMap<String, String>() {{
            put("error", e.getMessage());
        }};
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);

    }
}
