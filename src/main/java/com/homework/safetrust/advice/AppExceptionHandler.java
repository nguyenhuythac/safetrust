package com.homework.safetrust.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.homework.safetrust.exception.EntityNotFoundException;
import com.homework.safetrust.exception.UnmatchIDException;

/**
* 
* This class is used for unchecked exception handling.
* 
* Validation check.
* 
*/
@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EntityNotFoundException.class)
    public Map<String, String> handleUserNotFound(EntityNotFoundException ex){
        return mapErroMessage(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnmatchIDException.class)
    public Map<String, String> handleUserNotFound(UnmatchIDException ex){
        return mapErroMessage(ex.getMessage());
    }

    private Map<String, String> mapErroMessage(String message){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", message);
        return errorMap;
    }
}
