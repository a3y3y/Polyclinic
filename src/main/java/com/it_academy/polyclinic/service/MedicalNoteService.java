package com.it_academy.polyclinic.service;

import com.it_academy.polyclinic.model.patient_info.MedicalNote;
import com.it_academy.polyclinic.model.user_Info.User;
import com.it_academy.polyclinic.service.api.IMedicalNoteService;
import com.it_academy.polyclinic.storage.IMedicalNoteRepository;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MedicalNoteService implements IMedicalNoteService {

    private IMedicalNoteRepository medicalNoteRepository;

    public MedicalNoteService(IMedicalNoteRepository medicalNoteRepository) {
        this.medicalNoteRepository = medicalNoteRepository;
    }

    public MedicalNote addNote(MedicalNote medicalNote) {
        return medicalNoteRepository.save(medicalNote);
    }

    @Override
    public Set<MedicalNote> getNotesByUser(User user) {
        return medicalNoteRepository.findAllByUser(user);
    }


    @Override
    public Set<MedicalNote> getAll() {
        return medicalNoteRepository.findAllByOrderByDateAsc();
    }

    @Override
    public MedicalNote getNoteById(int id) {
        return medicalNoteRepository.findById(id);
    }

    @Override
    public boolean update(MedicalNote medicalNote, int id) {
        MedicalNote noteNew = medicalNoteRepository.findById(id);
        noteNew.setDescription(medicalNote.getDescription());
        noteNew.setName(medicalNote.getName());
        noteNew.setType(medicalNote.getType());
        noteNew.setIllness(medicalNote.getIllness());
        noteNew.setDate(medicalNote.getDate());
        noteNew.setId(medicalNote.getId());
        MedicalNote noteSaved = medicalNoteRepository.save(noteNew);
        if (noteSaved.equals(noteNew)) {
            return true;
        } else return false;
    }

    @Override
    public boolean delete(int id) {
        MedicalNote medicalNote = medicalNoteRepository.findById(id);
        if (medicalNote == null) {
            return false;
        } else {
            medicalNoteRepository.delete(medicalNote);
            return true;
        }
    }

    @Override
    public TreeSet<MedicalNote> getAllByUserId(int id) {
        return medicalNoteRepository.findAllByUserId(id);
    }
}
