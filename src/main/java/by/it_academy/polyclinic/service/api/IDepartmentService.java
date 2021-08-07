package by.it_academy.polyclinic.service.api;

import by.it_academy.polyclinic.model.doctor_info.Department;


import java.util.List;

public interface IDepartmentService {
    List<Department> getAll();
    Department getById(int id);
    boolean update(Department department, int id);
    Department getByName(String name);
    Department add(Department department);
    boolean delete(int id);
}
