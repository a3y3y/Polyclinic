package by.it_academy.polyclinic.service;

import by.it_academy.polyclinic.model.doctor_info.Specialization;
import by.it_academy.polyclinic.service.api.ISpecializationService;
import by.it_academy.polyclinic.storage.ISpecializationRepository;

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
        specializationNew.setId(specialization.getId());
        specializationNew.setName(specialization.getName());
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
}
