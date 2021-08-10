package com.it_academy.polyclinic.service.api;

import com.it_academy.polyclinic.model.dto.UserDto;
import com.it_academy.polyclinic.model.user_Info.Passport;
import com.it_academy.polyclinic.model.user_Info.Role;
import com.it_academy.polyclinic.model.user_Info.User;


import java.util.List;
import java.util.Set;


public interface IUserService {
    User getUserByEmail(String eMail);
    User registerUser(String eMail, String password, String tel);
    User getUserByPassport(Passport passport);
    User getUserById(int id);
    String getEmailById(int id);
    List<User> getAll();
    User addUser(User user);
    boolean updatePatient(User user, int id);
    boolean delete(int id);
    List<User> getAllUsersByRole(Set<Role> roles);
    Set<UserDto> getAllUsersInfoByRole(Set<Role> roles);
    User getByTicketId(Set<Role> roles, long id);
    boolean updateAddress(User user, int id);
    boolean updatePassport(User user, int id);
    boolean updateDoctorInfo(User user, int id);

}

