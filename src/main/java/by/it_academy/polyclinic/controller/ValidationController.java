package by.it_academy.polyclinic.controller;

import by.it_academy.polyclinic.model.user_Info.Address;
import by.it_academy.polyclinic.model.user_Info.Passport;
import by.it_academy.polyclinic.model.user_Info.User;
import by.it_academy.polyclinic.service.api.IPassportService;
import by.it_academy.polyclinic.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/cabinet")
public class ValidationController {

//    @Autowired
//    private IPassportService passportService;
//
//    @Autowired
//    private IUserService userService;

    @GetMapping("/validate")
    public String getPage(Model model){
//        model.addAttribute("users", userService.getAll());
        return "validate";
    }

//    @PostMapping("/validate")
//    public String validateUser(@ModelAttribute("user1") User userData,
//                               @ModelAttribute("passport") Passport passportData,
//                               @ModelAttribute("address") Address addressData){
//        User user = userService.getUserByEmail(userData.geteMail());
//
//        user.setPhoneNumber(userData.getPhoneNumber());
//        user.setRegistrationAddress(addressData);
//        user.setPassport(passportData);
//        userService.update(user, user.getId());
//        return "validate";
//    }

}
