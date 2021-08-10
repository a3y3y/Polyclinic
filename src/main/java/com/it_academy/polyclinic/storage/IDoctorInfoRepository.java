package com.it_academy.polyclinic.storage;

import com.it_academy.polyclinic.model.doctor_info.DoctorInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IDoctorInfoRepository extends JpaRepository<DoctorInfo, Integer> {
    DoctorInfo findById(int id);

    @Query("SELECT d FROM DoctorInfo d JOIN d.doctor u WHERE u.id=?1")
    DoctorInfo findByUserId(int id);
}
