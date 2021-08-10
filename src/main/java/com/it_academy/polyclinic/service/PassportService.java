package com.it_academy.polyclinic.service;

import com.it_academy.polyclinic.model.user_Info.Passport;
import com.it_academy.polyclinic.model.user_Info.User;
import com.it_academy.polyclinic.service.api.IPassportService;
import com.it_academy.polyclinic.storage.IPassportRepository;

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
    public Passport getPassportByNumber(String number) {
        return passportRepository.findByNumber(number);
    }

    @Override
    public Passport addPassport(Passport passport) {
        return passportRepository.save(passport);
    }

    @Override
    public List<Passport> getAll() {
       return passportRepository.findAll();
    }

    @Override
    public Passport getPassportById(int id) {
        return passportRepository.findById(id);
    }

    @Override
    public boolean update(Passport passport, int id) {
        Passport passportNew = passportRepository.findById(id);
        passportNew.setSex(passport.getSex());
        passportNew.setExpireDate(passport.getExpireDate());
        passportNew.setCodeOfIssuingState(passport.getCodeOfIssuingState());
        passportNew.setIssueDate(passport.getIssueDate());
        passportNew.setNumber(passport.getNumber());
        passportNew.setPatronymic(passport.getPatronymic());
        passportNew.setNationality(passport.getNationality());
        passportNew.setPersonalId(passport.getPersonalId());
        passportNew.setDateOfBirth(passport.getDateOfBirth());
        passportNew.setLastName(passport.getLastName());
        passportNew.setFirstName(passport.getFirstName());
        Passport passportSaved = passportRepository.save(passportNew);
        if(passportSaved.equals(passportNew)){
            return true;
        } else return false;
    }

    @Override
    public boolean delete(int id) {
        Passport passport = passportRepository.findById(id);
        if (passport == null) {
            return false;
        } else {
            passportRepository.delete(passport);
            return true;
        }
    }

    @Override
    public Passport getByUserId(int id) {
        return passportRepository.findByUserId(id);
    }
}
