package by.it_academy.polyclinic.service;

import by.it_academy.polyclinic.model.patient_info.MedicalCard;
import by.it_academy.polyclinic.model.user_Info.User;
import by.it_academy.polyclinic.service.api.IMedicalCardService;
import by.it_academy.polyclinic.storage.IMedicalCardRepository;

public class MedicalCardService implements IMedicalCardService {

    private IMedicalCardRepository medicalCardRepository;

    public MedicalCardService(IMedicalCardRepository medicalCardRepository) {
        this.medicalCardRepository = medicalCardRepository;
    }

    public MedicalCard getCardByUser(User user){
        return medicalCardRepository.findByUser(user);
    }

    @Override
    public void addCard(MedicalCard medicalCard) {
        medicalCardRepository.save(medicalCard);
    }
}
