package com.it_academy.polyclinic.service;

import com.it_academy.polyclinic.model.doctor_info.Specialization;
import com.it_academy.polyclinic.service.api.ISpecializationService;
import com.it_academy.polyclinic.storage.ISpecializationRepository;

import java.util.List;

public class SpecializationService implements ISpecializationService {

    private ISpecializationRepository specializationRepository;

    public SpecializationService(ISpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    @Override
    public List<Specialization> getAll() {
        return specializationRepository.findAll();
    }

    @Override
    public Specialization getById(int id) {
        return specializationRepository.findById(id);
    }

    @Override
    public boolean update(Specialization specialization, int id) {
        Specialization specializationNew = specializationRepository.findById(id);
        specializationNew.setDescription(specialization.getDescription());
        Specialization specializationSaved = specializationRepository.save(specializationNew);
        if(specializationSaved.equals(specializationNew)){
            return true;
        } else return false;
    }

    @Override
    public Specialization getByName(String name) {
        return specializationRepository.findByName(name);
    }

    @Override
    public Specialization add(Specialization specialization) {
        return specializationRepository.save(specialization);
    }

    @Override
    public boolean delete(int id) {
        Specialization specialization = specializationRepository.findById(id);
        if(specialization == null){
            return false;
        } else {
            specializationRepository.delete(specialization);
            return true;
        }
    }
}
