package by.it_academy.polyclinic.controller.rest;

import by.it_academy.polyclinic.model.patient_info.MedicalNote;
import by.it_academy.polyclinic.model.patient_info.MedicalNoteType;
import by.it_academy.polyclinic.service.api.IMedicalNoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical_notes")
public class MedicalNoteControllerRest {


    private final IMedicalNoteService medicalNoteService;

    public MedicalNoteControllerRest(IMedicalNoteService medicalNoteService) {
        this.medicalNoteService = medicalNoteService;
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
        MedicalNote medicalNoteNew = medicalNoteService.addNote(medicalNote);
        return new ResponseEntity<>(medicalNoteNew, HttpStatus.CREATED);
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

    @GetMapping("medical_note_type")
    public ResponseEntity<List<String>> getTypes(){
        List<String> medicalNoteTypes = MedicalNoteType.getNames();
        return new ResponseEntity<>(medicalNoteTypes, HttpStatus.OK);
    }

}
