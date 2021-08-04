package by.it_academy.polyclinic.service.api;

import by.it_academy.polyclinic.model.doctor_info.DoctorInfo;

import java.util.List;

public interface IDoctorInfoService {
    List<DoctorInfo> getAll();
    DoctorInfo getInfoById(int id);
    boolean update(DoctorInfo doctorInfo, int id);

}
