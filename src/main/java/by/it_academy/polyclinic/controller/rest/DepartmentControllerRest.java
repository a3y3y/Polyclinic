package by.it_academy.polyclinic.controller.rest;

import by.it_academy.polyclinic.model.doctor_info.Department;
import by.it_academy.polyclinic.service.api.IDepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentControllerRest {

    private final IDepartmentService departmentService;

    public DepartmentControllerRest(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<Department>> readAll() {
        List<Department> departments = departmentService.getAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Department> read(@PathVariable(name = "id") int id) {
        final Department department = departmentService.getById(id);

        return department != null
                ? new ResponseEntity<>(department, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Department> add(Department department) {
        Department departmentCheck = departmentService.getByName(department.getName());
        if(departmentCheck != null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            departmentService.add(department);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Department department) {
        final boolean updated = departmentService.update(department, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
