package by.it_academy.polyclinic.service;

import by.it_academy.polyclinic.model.user_Info.Passport;
import by.it_academy.polyclinic.model.user_Info.Role;
import by.it_academy.polyclinic.model.user_Info.User;
import by.it_academy.polyclinic.service.api.IUserService;
import by.it_academy.polyclinic.storage.IUserRepository;
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
    public User getUserByEmail(String eMail) {
        return userRepository.findByeMail(eMail);
    }

    public User getUserByPassport(Passport passport){
        return userRepository.findByPassport(passport);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean update(User user, int id) {
        User userNew = userRepository.findById(id);
        userNew.setPassword(user.getPassword());
        userNew.setPhoneNumber(user.getPhoneNumber());
        userNew.seteMail(userNew.geteMail());
        userNew.setPassport(user.getPassport());
        userNew.setRegistrationAddress(user.getRegistrationAddress());
        User userSaved = userRepository.save(userNew);
        if(userSaved.equals(userNew)){
            return true;
        } else return false;
    }

    @Override
    public boolean delete(int id) {
        User user = userRepository.findById(id);
        if(user == null){
            return false;
        } else {
            userRepository.delete(user);
            return true;
        }
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
