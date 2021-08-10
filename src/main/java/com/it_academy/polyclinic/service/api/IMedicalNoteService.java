package com.it_academy.polyclinic.service.api;

import com.it_academy.polyclinic.model.patient_info.MedicalNote;
import com.it_academy.polyclinic.model.user_Info.User;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public interface IMedicalNoteService {
    MedicalNote addNote(MedicalNote medicalNote);
    Set<MedicalNote> getNotesByUser(User user);
    Set<MedicalNote> getAll();
    MedicalNote getNoteById(int id);
    boolean update(MedicalNote medicalNote, int id);
    boolean delete(int id);
    TreeSet<MedicalNote> getAllByUserId(int id);
}
