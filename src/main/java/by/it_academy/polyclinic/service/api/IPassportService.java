package by.it_academy.polyclinic.service.api;

import by.it_academy.polyclinic.model.user_Info.Passport;

import java.util.List;

public interface IPassportService {
    Passport getPassportByFio(String lastName, String firstName, String patronymic, String passportNumber);
    Passport getPassportByNumber(String number);
    Passport addPassport(Passport passport, int userId);
    List<Passport> getAll();
    Passport getPassportById(int id);
    boolean update(Passport passport, int id);
    boolean delete(int id);
}
