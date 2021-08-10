package com.it_academy.polyclinic.service.api;

import com.it_academy.polyclinic.model.doctor_info.DoctorInfo;

import java.util.List;

public interface IDoctorInfoService {
    List<DoctorInfo> getAll();
    DoctorInfo getInfoById(int id);
    boolean update(DoctorInfo doctorInfo, int id);
    DoctorInfo add(DoctorInfo doctorInfo);
    DoctorInfo getByUserId(int id);

}
