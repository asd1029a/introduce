package com.kjm.introduce.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ExceptionController.java
 * Class 설명을 작성하세요.
 *
 * @author danusys
 * @since 2023.04.10
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public String exceptionMain() {
        return "error";
    }
}
