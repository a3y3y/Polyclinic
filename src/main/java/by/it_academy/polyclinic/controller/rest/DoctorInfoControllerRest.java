package by.it_academy.polyclinic.controller.rest;

import by.it_academy.polyclinic.model.doctor_info.Department;
import by.it_academy.polyclinic.model.doctor_info.DoctorInfo;
import by.it_academy.polyclinic.model.doctor_info.Specialization;
import by.it_academy.polyclinic.model.dto.Doctor;
import by.it_academy.polyclinic.model.user_Info.Role;
import by.it_academy.polyclinic.model.user_Info.User;
import by.it_academy.polyclinic.service.api.IDepartmentService;
import by.it_academy.polyclinic.service.api.IDoctorInfoService;
import by.it_academy.polyclinic.service.api.ISpecializationService;
import by.it_academy.polyclinic.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/doctor_info")
public class DoctorInfoControllerRest {

    private final IDoctorInfoService doctorInfoService;
    private final ISpecializationService specializationService;
    private final IDepartmentService departmentService;
    private final IUserService userService;

    public DoctorInfoControllerRest(IDoctorInfoService doctorInfoService, ISpecializationService specializationService, IDepartmentService departmentService, IUserService userService) {
        this.doctorInfoService = doctorInfoService;
        this.specializationService = specializationService;
        this.departmentService = departmentService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorInfo>> readAll() {
        List<DoctorInfo> doctorInfoList = doctorInfoService.getAll();
        return new ResponseEntity<>(doctorInfoList, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<DoctorInfo> read(@PathVariable(name = "id") int id) {
        final DoctorInfo doctorInfo = doctorInfoService.getInfoById(id);

        return doctorInfo != null
                ? new ResponseEntity<>(doctorInfo, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Doctor> add(Doctor doctor) {
        DoctorInfo doctorInfo = doctorInfoService.getInfoById(doctor.getId());
        if(doctorInfo != null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } else {
            doctorInfo = new DoctorInfo();
            Set<Specialization> specializations = new HashSet<>();
            doctor.getSpecializations().stream().forEach(n -> specializations
                    .add(specializationService.getById(n)));
            Set<Department> departments = new HashSet<>();
            doctor.getDepartments().stream().forEach(n -> departments
                    .add(departmentService.getById(n)));
            System.out.println(specializations);
            System.out.println(departments);
            doctorInfo.setSpecializations(specializations);
            doctorInfo.setDepartments(departments);
            User user = userService.getUserById(Integer.parseInt(doctor.getUserId()));
            Set<Role> roles = new HashSet<>();
            roles.add(Role.DOCTOR);
            user.setRoles(roles);
            doctorInfo.setRating(doctor.getRating());
            doctorInfo.setEducation(doctor.getEducation());
            doctorInfo.setLastPosition(doctor.getLastPosition());
            doctorInfo.setExperience(doctor.getExperience());
            doctorInfo.setLastPlaceOfWork(doctor.getLastPlaceOfWork());
            user.setDoctor(doctorInfo);
            doctorInfoService.add(doctorInfo);
            userService.update(user, user.getId());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody DoctorInfo doctorInfo) {
        final boolean updated = doctorInfoService.update(doctorInfo, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
