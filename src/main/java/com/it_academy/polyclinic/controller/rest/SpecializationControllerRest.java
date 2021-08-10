package com.it_academy.polyclinic.controller.rest;

import com.it_academy.polyclinic.model.doctor_info.Specialization;
import com.it_academy.polyclinic.service.api.ISpecializationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/specializations")
public class SpecializationControllerRest {

    private final ISpecializationService specializationService;

    public SpecializationControllerRest(ISpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @GetMapping
    public ResponseEntity<List<Specialization>> readAll() {
        List<Specialization> specializations = specializationService.getAll();
        return new ResponseEntity<>(specializations, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Specialization> read(@PathVariable(name = "id") int id) {
        final Specialization specialization = specializationService.getById(id);

        return specialization != null
                ? new ResponseEntity<>(specialization, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Specialization> add(Specialization specialization) {
        Specialization specializationCheck = specializationService.getByName(specialization.getName());
        if(specializationCheck != null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            specializationService.add(specialization);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Specialization specialization) {
        final boolean updated = specializationService.update(specialization, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = specializationService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
