package by.it_academy.polyclinic.service;

import by.it_academy.polyclinic.model.doctor_info.Department;
import by.it_academy.polyclinic.service.api.IDepartmentService;
import by.it_academy.polyclinic.storage.IDepartmentRepository;

import java.util.List;

public class DepartmentService implements IDepartmentService {

    private IDepartmentRepository departmentRepository;

    public DepartmentService(IDepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getById(int id) {
        return departmentRepository.findById(id);
    }

    @Override
    public boolean update(Department department, int id) {
        Department departmentNew = departmentRepository.findById(id);
        departmentNew.setDescription(department.getDescription());
        Department departmentSaved = departmentRepository.save(departmentNew);
        if(departmentSaved.equals(departmentNew)){
            return true;
        } else return false;
    }

    @Override
    public Department getByName(String name) {
        return departmentRepository.findByName(name);
    }

    @Override
    public Department add(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public boolean delete(int id) {
        Department department = departmentRepository.findById(id);
        if(department == null){
            return false;
        } else {
            departmentRepository.delete(department);
            return true;
        }
    }
}
