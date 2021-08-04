package by.it_academy.polyclinic.controller.rest;

import by.it_academy.polyclinic.model.patient_info.MedicalNote;
import by.it_academy.polyclinic.model.user_Info.Passport;
import by.it_academy.polyclinic.model.user_Info.User;
import by.it_academy.polyclinic.service.api.IMedicalNoteService;
import by.it_academy.polyclinic.service.api.IPassportService;
import by.it_academy.polyclinic.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical_notes")
public class MedicalNoteControllerRest {


    private final IMedicalNoteService medicalNoteService;
    private final IUserService userService;
    private final IPassportService passportService;

    public MedicalNoteControllerRest(IMedicalNoteService medicalNoteService, IUserService userService, IPassportService passportService) {
        this.medicalNoteService = medicalNoteService;
        this.userService = userService;
        this.passportService = passportService;
    }

    @GetMapping
    public ResponseEntity<List<MedicalNote>> readAll() {
        List<MedicalNote> medicalNotes = medicalNoteService.getAll();
        return new ResponseEntity<>(medicalNotes, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<MedicalNote> read(@PathVariable(name = "id") int id) {
        final MedicalNote medicalNote = medicalNoteService.getNoteById(id);

        return medicalNote != null
                ? new ResponseEntity<>(medicalNote, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<MedicalNote> addUser(MedicalNote medicalNote) {
        String passportNumber = medicalNote.getPassport();
        Passport passport = passportService.getPassportByNumber(passportNumber);
        User user = userService.getUserByPassport(passport);
        medicalNote.setUser(user);
        medicalNoteService.addNote(medicalNote);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody MedicalNote medicalNote) {
        final boolean updated = medicalNoteService.update(medicalNote, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = medicalNoteService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }



}
