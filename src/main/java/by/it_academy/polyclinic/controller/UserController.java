package by.it_academy.polyclinic.controller;

import by.it_academy.polyclinic.model.user_Info.User;
import by.it_academy.polyclinic.service.api.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes(value = "user")
public class UserController {
    IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String getUserRegistrationPage(){
        return "/view/registration.jsp";
    }

    @PostMapping("/registration")
    public String userRegistration(@RequestParam(name = "eMail") String email,
                                   @RequestParam(name = "password") String password,
                                   HttpServletRequest req){
        try {
            User user = userService.registerUser(email, password);
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("role", user.getRoles());
            return "redirect: " + req.getContextPath() + "/";
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", true);
            req.setAttribute("message", e.getMessage());
            return "/view/registration.jsp";
        }
    }

    @GetMapping("/signIn")
    public String getSignInPage(){
        return "/view/signIn.jsp";
    }

//    @PostMapping("/signIn")
//    public String signIn(@RequestParam(name = "eMail") String eMail,
//                         @RequestParam(name = "password") String password,
//                         HttpServletRequest req){
//        if(userService.checkPasswordAndLogin(eMail, password)){
//            User user = userService.getUserByEmail(eMail);
//            req.getSession().setAttribute("user", user);
//            req.getSession().setAttribute("role", user.getRoles());
//            return "redirect: " + req.getContextPath() + "/view/user_cabinet.jsp";
//        }
//        req.setAttribute("error", true);
//        req.setAttribute("message", "Не верный логин или пароль");
//        return "/view/signIn.jsp";
//    }

    @GetMapping("/cabinet")
    public String getUserCabinet(){
        return "/view/cabinet.jsp";
    }
}
