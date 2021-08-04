package by.it_academy.polyclinic.service.api;

import by.it_academy.polyclinic.model.user_Info.Passport;
import by.it_academy.polyclinic.model.user_Info.User;


import java.util.List;


public interface IUserService {
    User getUserByEmail(String eMail);
    User registerUser(String eMail, String password, String tel);
    User getUserByPassport(Passport passport);
    User getUserById(int id);
    List<User> getAll();
    User addUser(User user);
    boolean update(User user, int id);
    boolean delete(int id);

}

