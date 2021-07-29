package by.it_academy.polyclinic.controller;

import by.it_academy.polyclinic.model.patient_info.MedicalCard;
import by.it_academy.polyclinic.model.patient_info.MedicalNote;
import by.it_academy.polyclinic.model.patient_info.MedicalNoteType;
import by.it_academy.polyclinic.model.user_Info.Passport;
import by.it_academy.polyclinic.model.user_Info.User;
import by.it_academy.polyclinic.service.api.IMedicalCardService;
import by.it_academy.polyclinic.service.api.IPassportService;
import by.it_academy.polyclinic.service.api.IUserService;
import org.geolatte.geom.M;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cabinet")
public class MedicalCardController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IMedicalCardService medicalCardService;
    @Autowired
    private IPassportService passportService;

    @GetMapping("/medical_card")
    public String getPage(Principal principal, Model model){
        model.addAttribute("passports", passportService.getAllPassports());
        return "medical_card";
    }

    @PostMapping("/medical_card")
    public String addNote(@RequestParam(name = "date") String date, @RequestParam(name = "type") String type,
                          @RequestParam(name = "name") String name, @RequestParam(name = "illness") String illness,
                          @RequestParam(name = "description") String description
            , @RequestParam(name = "fio") String fio, HttpServletRequest req){
        req.setAttribute("noteTypesList", MedicalNoteType.values());
        String[] data = fio.split(" ");
        String lastName = data[0];
        String firstName = data[1];
        String patronymic = data[2];
        String number = data[3];

        Passport passport = passportService.getPassportByFio(lastName,firstName,patronymic,number);
        User user = userService.getUserByPassport(passport);
        MedicalCard medicalCard = medicalCardService.getCardByUser(user);
        MedicalNote medicalNote = new MedicalNote();
        medicalNote.setDate(date);
        medicalNote.setIllness(illness);
        medicalNote.setType(MedicalNoteType.valueOf(type));
        medicalNote.setName(name);
        medicalNote.setDescription(description);
        medicalCard.addNote(medicalNote);
        return "medical_card";
    }

    @GetMapping("/new_medical_card")
    public String getMedPage(Principal principal, Model model){
        model.addAttribute("passports", passportService.getAllPassports());
        return "medical_card";
    }

    @PostMapping("/new_medical_card")
    public String createCard(@RequestParam(name = "date") String date, @RequestParam(name = "type") String type,
                          @RequestParam(name = "name") String name, @RequestParam(name = "illness") String illness,
                          @RequestParam(name = "description") String description
            , @RequestParam(name = "fio") String fio, HttpServletRequest req){
        req.setAttribute("noteTypesList", MedicalNoteType.values());
        String[] data = fio.split(" ");
        String lastName = data[0];
        String firstName = data[1];
        String patronymic = data[2];
        String number = data[3];

        Passport passport = passportService.getPassportByFio(lastName,firstName,patronymic,number);
        User user = userService.getUserByPassport(passport);
        MedicalCard medicalCard = new MedicalCard();
        MedicalNote medicalNote = new MedicalNote();
        medicalNote.setDate(date);
        medicalNote.setIllness(illness);

        medicalNote.setName(name);
        medicalNote.setDescription(description);
        Set<MedicalNote> notes = new HashSet<>();
        notes.add(medicalNote);
        medicalCard.setNotes(notes);
        medicalCard.setUser(user);
        medicalCardService.addCard(medicalCard);
        return "medical_card";
    }

}
