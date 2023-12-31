package com.grubneac.CafeDemoCRM.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessDeniedPageController {

    @GetMapping("/showAccessDeniedPage")
    String forbiddenPage(){
        return "access-denied";
    }
}
