package by.it_academy.polyclinic.storage;

import by.it_academy.polyclinic.model.patient_info.MedicalCard;
import by.it_academy.polyclinic.model.user_Info.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicalCardRepository extends JpaRepository<MedicalCard, Integer> {
    MedicalCard findByUser(User user);
}
