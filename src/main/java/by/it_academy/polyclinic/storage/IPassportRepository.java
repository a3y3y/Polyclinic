package by.it_academy.polyclinic.storage;

import by.it_academy.polyclinic.model.user_Info.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPassportRepository extends JpaRepository<Passport, Integer> {
    Passport findByLastNameAndFirstNameAndPatronymicAndNumber(String lastName, String firstName,
                                                     String patronymic, String number);
    Passport findById(int id);

}
