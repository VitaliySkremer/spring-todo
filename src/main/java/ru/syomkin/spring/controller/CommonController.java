package ru.syomkin.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prefix")
public class CommonController {

    @RequestMapping("/home")
    public String getMainPage() {
        return "main-page";
    }
}
