package by.it_academy.polyclinic.controller.rest;

import by.it_academy.polyclinic.model.user_Info.Passport;
import by.it_academy.polyclinic.model.user_Info.User;
import by.it_academy.polyclinic.service.api.IPassportService;
import by.it_academy.polyclinic.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        user.setPassport(passport);
        userService.update(user, passport.getUserId());
        return new ResponseEntity<>(passport, HttpStatus.CREATED);
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
