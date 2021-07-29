package by.it_academy.polyclinic.storage;

import by.it_academy.polyclinic.model.user_Info.Passport;
import by.it_academy.polyclinic.model.user_Info.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IUserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByeMail(String eMail);
    User findByeMail(String eMail);
    User findByPassport(Passport passport);
}
