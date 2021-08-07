package by.it_academy.polyclinic.storage;

import by.it_academy.polyclinic.model.dto.UserDto;
import by.it_academy.polyclinic.model.user_Info.Passport;
import by.it_academy.polyclinic.model.user_Info.Role;
import by.it_academy.polyclinic.model.user_Info.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;


public interface IUserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByeMail(String eMail);
    User findByeMail(String eMail);
    User findByPassport(Passport passport);
    User findById(int id);
    List<User> findByRolesIn(Set<Role> roles);

    @Query("SELECT u.id FROM User u INNER JOIN u.roles WHERE role='DOCTOR'")
    Set<Integer> findAllDoctorsIds();

    @Query("SELECT new by.it_academy.polyclinic.model.dto.UserDto(u.id, p.lastName, p.firstName, p.number) " +
            "FROM User u INNER JOIN u.passport AS p " +
            "INNER JOIN u.roles WHERE role='DOCTOR'")
    TypedQuery<UserDto> findDoctorsInfos();
}
