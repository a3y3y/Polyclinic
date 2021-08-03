package by.it_academy.polyclinic.service;

import by.it_academy.polyclinic.model.patient_info.MedicalCard;
import by.it_academy.polyclinic.model.patient_info.MedicalNote;
import by.it_academy.polyclinic.model.user_Info.Passport;
import by.it_academy.polyclinic.service.api.IMedicalNoteService;
import by.it_academy.polyclinic.storage.IMedicalNoteRepository;

import java.util.List;
import java.util.Set;

public class MedicalNoteService implements IMedicalNoteService {

    private IMedicalNoteRepository medicalNoteRepository;

    public MedicalNoteService(IMedicalNoteRepository medicalNoteRepository) {
        this.medicalNoteRepository = medicalNoteRepository;
    }

    public MedicalNote addNote(MedicalNote medicalNote) {
        return medicalNoteRepository.save(medicalNote);
    }

    public Set<MedicalNote> getNotesByCard(MedicalCard medicalCard) {
        return medicalNoteRepository.findAllByMedicalCard(medicalCard);
    }

    @Override
    public List<MedicalNote> getAll() {
        return medicalNoteRepository.findAll();
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
}
