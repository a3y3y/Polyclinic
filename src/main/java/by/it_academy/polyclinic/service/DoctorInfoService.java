package by.it_academy.polyclinic.service;

import by.it_academy.polyclinic.model.doctor_info.DoctorInfo;
import by.it_academy.polyclinic.service.api.IDoctorInfoService;
import by.it_academy.polyclinic.storage.IDoctorInfoRepository;

import java.util.List;

public class DoctorInfoService implements IDoctorInfoService {

    private IDoctorInfoRepository doctorInfoRepository;

    public DoctorInfoService(IDoctorInfoRepository doctorInfoRepository) {
        this.doctorInfoRepository = doctorInfoRepository;
    }

    @Override
    public List<DoctorInfo> getAll() {
        return doctorInfoRepository.findAll();
    }

    @Override
    public DoctorInfo getInfoById(int id) {
        return doctorInfoRepository.findById(id);
    }

    @Override
    public boolean update(DoctorInfo doctorInfo, int id) {
        DoctorInfo doctorInfoNew = doctorInfoRepository.findById(id);
        doctorInfoNew.setExperience(doctorInfo.getExperience());
        doctorInfoNew.setUserId(doctorInfo.getUserId());
        doctorInfoNew.setEducation(doctorInfo.getEducation());
        doctorInfoNew.setLastPlaceOfWork(doctorInfo.getLastPlaceOfWork());
        doctorInfoNew.setLastPosition(doctorInfo.getLastPosition());
        doctorInfoNew.setRating(doctorInfo.getRating());
        DoctorInfo doctorInfoSaved = doctorInfoRepository.save(doctorInfoNew);
        if(doctorInfoSaved.equals(doctorInfoNew)){
            return true;
        } else return false;
    }
}
