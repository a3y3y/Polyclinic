package com.it_academy.polyclinic.storage;

import com.it_academy.polyclinic.model.doctor_info.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISpecializationRepository extends JpaRepository<Specialization, Integer> {

    Specialization findById(int id);
    Specialization findByName(String name);
}
