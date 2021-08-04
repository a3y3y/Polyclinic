package by.it_academy.polyclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cabinet")
public class MedicalCardController {

    @GetMapping("/medical_card")
    public String getPage(){
        return "medical_card";
    }

}
