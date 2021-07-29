package by.it_academy.polyclinic.service.api;

import by.it_academy.polyclinic.model.patient_info.MedicalCard;
import by.it_academy.polyclinic.model.patient_info.MedicalNote;

import java.util.Set;

public interface IMedicalNoteService {
    void addNote(MedicalNote medicalNote);
    Set<MedicalNote> getNotesByCard(MedicalCard medicalCard);
}
