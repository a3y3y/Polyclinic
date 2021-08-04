package by.it_academy.polyclinic.service.api;

import by.it_academy.polyclinic.model.patient_info.MedicalNote;
import by.it_academy.polyclinic.model.user_Info.User;

import java.util.List;
import java.util.Set;

public interface IMedicalNoteService {
    MedicalNote addNote(MedicalNote medicalNote);
    Set<MedicalNote> getNotesByUser(User user);
    List<MedicalNote> getAll();
    MedicalNote getNoteById(int id);
    boolean update(MedicalNote medicalNote, int id);
    boolean delete(int id);
}
