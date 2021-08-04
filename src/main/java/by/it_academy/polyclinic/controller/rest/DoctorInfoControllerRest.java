package by.it_academy.polyclinic.controller.rest;

import by.it_academy.polyclinic.model.doctor_info.DoctorInfo;
import by.it_academy.polyclinic.service.api.IDoctorInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor_info")
public class DoctorInfoControllerRest {

    private final IDoctorInfoService doctorInfoService;


    public DoctorInfoControllerRest(IDoctorInfoService doctorInfoService) {
        this.doctorInfoService = doctorInfoService;
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
    public ResponseEntity<DoctorInfo> add(DoctorInfo doctorInfo) {

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
