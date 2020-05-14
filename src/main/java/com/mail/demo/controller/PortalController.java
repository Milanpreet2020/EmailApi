package com.mail.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PortalController {
    @GetMapping(value="/index")
    public String indexPage(){
        return "index";
    }
}
