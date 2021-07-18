package by.it_academy.polyclinic.service.api;

import by.it_academy.polyclinic.model.user_Info.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    public List<User> getAll();
    public User getUserByEmail(String eMail);
    public User registerUser(String eMail, String password);
    public boolean checkPasswordAndLogin(String eMail, String password);
}

