package by.it_academy.polyclinic.storage;

import by.it_academy.polyclinic.model.doctor_info.DoctorInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorInfoRepository extends JpaRepository<DoctorInfo, Integer> {
    DoctorInfo findById(int id);
}
