package com.ibs.training.ExpediaProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @GetMapping("/")
    public String home(){
        return "home";
    }
    @RequestMapping(value="/login",method = {RequestMethod.GET, RequestMethod.PUT})
    public String loginPage() {
        return "login";
    }
    @GetMapping("/logout-success")
    public String logoutPage() {
        return "logout";
    }

}
