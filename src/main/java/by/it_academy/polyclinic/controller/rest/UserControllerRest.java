package by.it_academy.polyclinic.controller.rest;

import by.it_academy.polyclinic.model.user_Info.User;
import by.it_academy.polyclinic.service.api.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserControllerRest {

    @Autowired
    private final IUserService userService;
    @Autowired
    private final ObjectMapper mapper;

    public UserControllerRest(IUserService userService, ObjectMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping(value = "/users", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/registration")
    public ResponseEntity<User> addUser(User user) {
        User userNew = userService.addUser(user);
        return new ResponseEntity<>(userNew, HttpStatus.CREATED);
    }


    public String userRegistration(@RequestParam(name = "eMail") String email,
                                   @RequestParam(name = "password") String password,
                                   HttpServletRequest req) {
        try {
            User user = userService.registerUser(email, password);
//            req.setAttribute("username", user.geteMail());
//            req.setAttribute("password", user.getPassword());
            return "redirect: " + req.getContextPath() + "/signIn";
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", true);
            req.setAttribute("message", e.getMessage());
            return "registration";
        }
    }

}
