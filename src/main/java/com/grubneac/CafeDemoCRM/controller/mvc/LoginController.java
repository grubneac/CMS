package com.grubneac.CafeDemoCRM.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    String login(){
        return "fancy-login";
    }
}
