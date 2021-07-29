package by.it_academy.polyclinic.service.api;

import by.it_academy.polyclinic.model.patient_info.MedicalCard;
import by.it_academy.polyclinic.model.patient_info.MedicalNote;
import by.it_academy.polyclinic.model.user_Info.User;

public interface IMedicalCardService {
    MedicalCard getCardByUser(User user);
    void addCard(MedicalCard medicalCard);
}
