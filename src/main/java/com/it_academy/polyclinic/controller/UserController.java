package com.it_academy.polyclinic.controller;

import com.it_academy.polyclinic.model.user_Info.User;
import com.it_academy.polyclinic.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/registration")
    public String getUserRegistrationPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public String userRegistration(@RequestParam(name = "eMail") String email,
                                   @RequestParam(name = "password") String password,
                                   @RequestParam(name = "tel") String tel,
                                   HttpServletRequest req){
        try {
            User user = userService.registerUser(email, password, tel);
//            req.setAttribute("username", user.geteMail());
//            req.setAttribute("password", user.getPassword());
            return "redirect: " + req.getContextPath() + "/signIn";
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", true);
            req.setAttribute("message", e.getMessage());
            return "registration";
        }
    }

    @GetMapping("/signIn")
    public String getSignInPage(){
        return "signIn";
    }

}
