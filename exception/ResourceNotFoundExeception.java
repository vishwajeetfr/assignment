package com.leucine.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundExeception extends RuntimeException{
    public ResourceNotFoundExeception(String message){
        super(message);
    }
}
