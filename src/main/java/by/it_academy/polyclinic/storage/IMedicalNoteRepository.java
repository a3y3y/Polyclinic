package by.it_academy.polyclinic.storage;

import by.it_academy.polyclinic.model.patient_info.MedicalCard;
import by.it_academy.polyclinic.model.patient_info.MedicalNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface IMedicalNoteRepository extends JpaRepository<MedicalNote, Long> {
    Set<MedicalNote> findAllByMedicalCard(MedicalCard medicalCard);
}
