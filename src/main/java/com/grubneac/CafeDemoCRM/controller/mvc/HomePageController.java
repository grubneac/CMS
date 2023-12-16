package com.grubneac.CafeDemoCRM.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/home")
    String homePage(){
        return "home";
    }
    @GetMapping("/")
    String rootPage(){
        return "home";
    }
}
