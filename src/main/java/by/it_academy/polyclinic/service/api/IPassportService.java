package by.it_academy.polyclinic.service.api;

import by.it_academy.polyclinic.model.user_Info.Passport;

import java.util.List;

public interface IPassportService {
    Passport getPassportByFio(String lastName, String firstName, String patronymic, String passportNumber);
    void addPassport(Passport passport);
    List<Passport> getAllPassports();
}
