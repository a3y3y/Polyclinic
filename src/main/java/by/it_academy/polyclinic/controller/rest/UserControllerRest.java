package by.it_academy.polyclinic.controller.rest;

import by.it_academy.polyclinic.model.user_Info.User;
import by.it_academy.polyclinic.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserControllerRest {


    private final IUserService userService;


    public UserControllerRest(IUserService userService) {
        this.userService = userService;

    }

    @GetMapping
    public ResponseEntity<List<User>> readAll() {
        List<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/eMails")
    public ResponseEntity<List<User>> readAllEmails() {
        List<User> usersEmails = new ArrayList<>();
        for (User n : userService.getAll()) {
            User user = new User();
            user.seteMail(n.geteMail());
            user.setId(n.getId());
            usersEmails.add(user);
        }
        return new ResponseEntity<>(usersEmails, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> read(@PathVariable(name = "id") int id) {
        final User user = userService.getUserById(id);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<User> addUser(User user) {
        User userNew = userService.addUser(user);
        return new ResponseEntity<>(userNew, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody User user) {
        final boolean updated = userService.update(user, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = userService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
