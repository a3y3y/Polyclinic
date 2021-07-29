package by.it_academy.polyclinic.service;

import by.it_academy.polyclinic.model.patient_info.MedicalCard;
import by.it_academy.polyclinic.model.patient_info.MedicalNote;
import by.it_academy.polyclinic.service.api.IMedicalNoteService;
import by.it_academy.polyclinic.storage.IMedicalNoteRepository;

import java.util.Set;

public class MedicalNoteService implements IMedicalNoteService {

    private IMedicalNoteRepository medicalNoteRepository;

    public MedicalNoteService(IMedicalNoteRepository medicalNoteRepository) {
        this.medicalNoteRepository = medicalNoteRepository;
    }

    public void addNote(MedicalNote medicalNote){
        medicalNoteRepository.save(medicalNote);
    }

    public Set<MedicalNote> getNotesByCard(MedicalCard medicalCard){
        return medicalNoteRepository.findAllByMedicalCard(medicalCard);
    }
}
