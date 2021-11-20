package com.vtt.bt.controller;

import com.vtt.bt.common.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionControler {
    @ExceptionHandler(value = ResponseException.class)
    public ResponseEntity<Object> exception(HttpServletRequest http, ResponseException exception) {
        Map<String, String> ma = new HashMap<>();
        ma.put("message", exception.getMessageError());
        return new ResponseEntity<Object>(ma, HttpStatus.valueOf(exception.getCode()));
    }
}