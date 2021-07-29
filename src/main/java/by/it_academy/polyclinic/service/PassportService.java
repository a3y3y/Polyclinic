package by.it_academy.polyclinic.service;

import by.it_academy.polyclinic.model.user_Info.Passport;
import by.it_academy.polyclinic.service.api.IPassportService;
import by.it_academy.polyclinic.storage.IPassportRepository;

import java.util.List;

public class PassportService implements IPassportService {

    private IPassportRepository passportRepository;

    public PassportService(IPassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    @Override
    public Passport getPassportByFio(String lastName, String firstName, String patronymic, String passportNumber) {
        return passportRepository
                .findByLastNameAndFirstNameAndPatronymicAndNumber(lastName, firstName, patronymic, passportNumber);
    }

    @Override
    public void addPassport(Passport passport) {
        passportRepository.save(passport);
    }

    @Override
    public List<Passport> getAllPassports() {
       return passportRepository.findAll();
    }
}
