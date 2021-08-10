package com.it_academy.polyclinic.controller.rest;

import com.it_academy.polyclinic.model.dto.UserDto;
import com.it_academy.polyclinic.model.user_Info.Passport;
import com.it_academy.polyclinic.model.user_Info.Role;
import com.it_academy.polyclinic.model.user_Info.User;
import com.it_academy.polyclinic.service.api.IPassportService;
import com.it_academy.polyclinic.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/passports")
public class PassportControllerRest {


    private final IPassportService passportService;
    private final IUserService userService;


    public PassportControllerRest(IPassportService passportService, IUserService userService) {
        this.passportService = passportService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Passport>> readAll() {
        List<Passport> passports = passportService.getAll();
        return new ResponseEntity<>(passports, HttpStatus.OK);
    }

    @GetMapping("/doctors")
    public ResponseEntity<Set<UserDto>> readAllDoctorsPassports() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.DOCTOR);
        Set<UserDto> userDto = userService.getAllUsersInfoByRole(roles);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/patients")
    public ResponseEntity<Set<UserDto>> readAllPatientsPassports() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.PATIENT);
        Set<UserDto> userDto = userService.getAllUsersInfoByRole(roles);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/doctors_patients")
    public ResponseEntity<Set<UserDto>> readAllPatientsAndDoctorsPassports() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.PATIENT);
        roles.add(Role.DOCTOR);
        Set<UserDto> userDto = userService.getAllUsersInfoByRole(roles);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passport> read(@PathVariable(name = "id") int id) {
        final Passport passport = passportService.getPassportById(id);

        return passport != null
                ? new ResponseEntity<>(passport, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Passport> add(Passport passport) {
        User user = userService.getUserById(passport.getUserId());
        if(user.getPassport() != null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            Passport passportNew = new Passport(passport.getLastName(), passport.getFirstName(),
                    passport.getPatronymic(), passport.getCodeOfIssuingState(), passport.getNumber(),
                    passport.getPersonalId(), passport.getNationality(), passport.getDateOfBirth(),
                    passport.getSex(), passport.getIssueDate(), passport.getExpireDate());
            user.setPassport(passportNew);

            userService.updatePassport(user, passport.getUserId());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Passport passport) {
        final boolean updated = passportService.update(passport, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = passportService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
