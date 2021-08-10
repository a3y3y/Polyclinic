package com.it_academy.polyclinic.service;

import com.it_academy.polyclinic.model.doctor_info.Department;
import com.it_academy.polyclinic.model.doctor_info.DoctorInfo;
import com.it_academy.polyclinic.model.doctor_info.Specialization;
import com.it_academy.polyclinic.model.dto.UserDto;
import com.it_academy.polyclinic.model.user_Info.Passport;
import com.it_academy.polyclinic.model.user_Info.Role;
import com.it_academy.polyclinic.model.user_Info.User;
import com.it_academy.polyclinic.service.api.IUserService;
import com.it_academy.polyclinic.storage.IDepartmentRepository;
import com.it_academy.polyclinic.storage.IDoctorInfoRepository;
import com.it_academy.polyclinic.storage.ISpecializationRepository;
import com.it_academy.polyclinic.storage.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private IUserRepository userRepository;

    private IDepartmentRepository departmentRepository;

    private ISpecializationRepository specializationRepository;

    private IDoctorInfoRepository doctorInfoRepository;

    public UserService(IUserRepository userRepository, IDepartmentRepository departmentRepository, ISpecializationRepository specializationRepository, IDoctorInfoRepository doctorInfoRepository) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.specializationRepository = specializationRepository;
        this.doctorInfoRepository = doctorInfoRepository;
    }

    @Override
    public User getUserByEmail(String eMail) {
        return userRepository.findByeMail(eMail);
    }

    public User getUserByPassport(Passport passport) {
        return userRepository.findByPassport(passport);
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public String getEmailById(int id) {
        return userRepository.findEmailById(id);
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
    public boolean updatePatient(User user, int id) {
        User userNew = userRepository.findById(id);
        if (!(user.getPassword() == null || user.getPassword().isEmpty())) {
            userNew.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (!(user.getPhoneNumber() == null || user.getPhoneNumber().isEmpty())) {
            userNew.setPhoneNumber(user.getPhoneNumber());
        }

        if (!user.getRoles().isEmpty()) {
            userNew.setRoles(user.getRoles());
        }
        User userSaved = userRepository.save(userNew);
        if (userSaved.equals(userNew)) {
            return true;
        } else return false;
    }

    @Override
    public boolean delete(int id) {
        User user = userRepository.findById(id);
        if (user == null) {
            return false;
        } else {
            userRepository.delete(user);
            return true;
        }
    }

    @Override
    public List<User> getAllUsersByRole(Set<Role> roles) {
        return userRepository.findByRolesIn(roles);
    }

    @Override
    public Set<UserDto> getAllUsersInfoByRole(Set<Role> roles) {
        return userRepository.findUsersInfoByRole(roles);
    }

    @Override
    public User getByTicketId(Set<Role> roles, long id) {
        return userRepository.findByTicketId(roles, id);
    }

    @Override
    public boolean updateAddress(User user, int id) {
        User userNew = userRepository.findById(id);
        userNew.setRegistrationAddress(user.getRegistrationAddress());
        User userSaved = userRepository.save(userNew);
        if (userSaved.equals(userNew)) {
            return true;
        } else return false;
    }

    @Override
    public boolean updatePassport(User user, int id) {
        User userNew = userRepository.findById(id);
        userNew.setPassport(user.getPassport());
        userNew.setActive(true);
        User userSaved = userRepository.save(userNew);
        if (userSaved.equals(userNew)) {
            return true;
        } else return false;
    }

    @Transactional
    @Override
    public boolean updateDoctorInfo(User user, int id) {
        User userNew = userRepository.findById(id);
        userNew.setDoctor(user.getDoctor());
        doctorInfoRepository.save(userNew.getDoctor());
        List<DoctorInfo> doctorInfoList = new ArrayList<>();
        doctorInfoList.add(user.getDoctor());
        for (Department dep : userNew.getDoctor().getDepartments()) {
            dep.setDoctorInfo(doctorInfoList);
            departmentRepository.save(dep);
        }
        for (Specialization spec : userNew.getDoctor().getSpecializations()) {
            spec.setDoctorInfo(doctorInfoList);
            specializationRepository.save(spec);
        }
        Set<Role> roles = new HashSet<>();
        roles.add(Role.DOCTOR);
        userNew.setRoles(roles);
        User userSaved = userRepository.save(userNew);
        if (userSaved.equals(userNew)) {
            return true;
        } else return false;
    }


    public User registerUser(String eMail, String password, String tel) {
        User user = new User();
        Set<Role> roles = new HashSet<>();
        roles.add(Role.PATIENT);
        user.setRoles(roles);
        user.seteMail(eMail);
        user.setPhoneNumber(tel);
        user.setPassword(passwordEncoder.encode(password));
        user.setActive(false);
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
            User userFindByUsername = userRepository.findByeMail(username);


            if (userFindByUsername != null) {
                return userFindByUsername;
            }

            return null;
        }
    }
}
