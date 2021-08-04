package by.it_academy.polyclinic.service.api;

import by.it_academy.polyclinic.model.doctor_info.Specialization;

import java.util.List;

public interface ISpecializationService {
    List<Specialization> getAll();
    Specialization getById(int id);
    boolean update(Specialization specialization, int id);
    Specialization getByName(String name);
    Specialization add(Specialization specialization);
}
