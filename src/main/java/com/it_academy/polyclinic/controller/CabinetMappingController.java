package com.it_academy.polyclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cabinet")
public class CabinetMappingController {

    @GetMapping("/medical_card")
    public String getMedicalCard(){
        return "medical_card";
    }

    @GetMapping
    public String getUserCabinet(){
        return "cabinet";
    }

    @GetMapping("/users")
    public String getUsers(){return "users";}

    @GetMapping("/doctor")
    public String getDoctorPage(){
        return "doctor";
    }

    @GetMapping("/specializations")
    public String getSpecializationPage(){
        return "specialization";
    }

    @GetMapping("/departments")
    public String getDepartmentPage(){
        return "department";
    }

    @GetMapping("/tickets")
    public String getDoctorTickets(){
        return "tickets";
    }

    @GetMapping("/ticket_order")
    public String getOrderTickets(){
        return "ticket_order";
    }

    @GetMapping("/my_tickets")
    public String getMyTickets(){
        return "my_tickets";
    }

    @GetMapping("/validate")
    public String getPage(Model model){
        return "validate";
    }

    @GetMapping("/redact_user")
    public String getRedactPage(Model model){
        return "redact_user";
    }

    @GetMapping("/change_user_role")
    public String getChangeRolePage(Model model){
        return "change_user_role";
    }

    @GetMapping("/ticket_order_patient")
    public String getPatientTicketOrderPage(Model model){
        return "ticket_order_patient";
    }
}
