package by.it_academy.polyclinic.service;

import by.it_academy.polyclinic.model.user_Info.User;
import by.it_academy.polyclinic.service.api.IUserService;
import by.it_academy.polyclinic.storage.IUserRepository;
import by.it_academy.polyclinic.model.user_Info.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getUserByEmail(String eMail) {
        return userRepository.findByeMail(eMail);
    }

    public boolean checkPasswordAndLogin(String eMail, String password){
       List<User> users = userRepository.findAllByeMail(eMail);
       if(users.isEmpty()){
           return false;
       }
       if(users.get(0).getPassword().equals(password)){
           return true;
       }
       return false;
    }

    public User registerUser(String eMail, String password){
        User user = new User();
        Set<Role> roles = new HashSet<>();
        roles.add(Role.PATIENT);
        user.setRoles(roles);
        user.seteMail(eMail);
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(true);
        validationForSignUp(user);
        userRepository.save(user);
        return user;
    }
    private void validationForSignUp(User user) {
        String errorMessage = "";
        if (this.nullOrEmpty(user.geteMail())) {
            errorMessage += "E-mail обязателен для заполнения";
        }
        if (this.nullOrEmpty(user.getPassword())) {
            if (!errorMessage.isEmpty()) {
                errorMessage += "; ";
            }
            errorMessage += "Пароль обязателен для заполнения";
        }

        if (!errorMessage.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
        if (!userRepository.findAllByeMail(user.geteMail()).isEmpty()) {
            throw new IllegalArgumentException("Этот почтовый адрес уже существует");
        }
    }
    private boolean nullOrEmpty(String val) {
        return val == null || val.isEmpty();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        {
            User userFindByUsername =userRepository.findByeMail(username);


            if(userFindByUsername != null)
            {
                return userFindByUsername;
            }

            return null;
        }
    }
}
