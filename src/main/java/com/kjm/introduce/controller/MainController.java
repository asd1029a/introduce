package com.kjm.introduce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * MainController.java
 * 기본 화면 컨트롤러
 *
 * @author kjm
 * @since 2023.04.06
 */
@Controller
public class MainController {

    @GetMapping
    public String main() {
        return "index";
    }
}
