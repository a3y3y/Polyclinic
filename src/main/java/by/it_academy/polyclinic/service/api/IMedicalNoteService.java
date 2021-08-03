package by.it_academy.polyclinic.service.api;

import by.it_academy.polyclinic.model.patient_info.MedicalCard;
import by.it_academy.polyclinic.model.patient_info.MedicalNote;
import by.it_academy.polyclinic.model.user_Info.Passport;

import java.util.List;
import java.util.Set;

public interface IMedicalNoteService {
    MedicalNote addNote(MedicalNote medicalNote);
    Set<MedicalNote> getNotesByCard(MedicalCard medicalCard);
    List<MedicalNote> getAll();
    MedicalNote getNoteById(int id);
    boolean update(MedicalNote medicalNote, int id);
    boolean delete(int id);
}
