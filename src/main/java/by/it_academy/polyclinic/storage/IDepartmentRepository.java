package by.it_academy.polyclinic.storage;

import by.it_academy.polyclinic.model.doctor_info.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartmentRepository extends JpaRepository<Department, Integer> {
    Department findById(int id);
    Department findByName(String name);
}
