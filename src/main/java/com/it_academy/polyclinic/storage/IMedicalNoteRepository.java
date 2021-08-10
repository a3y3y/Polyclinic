package com.it_academy.polyclinic.storage;

import com.it_academy.polyclinic.model.patient_info.MedicalNote;
import com.it_academy.polyclinic.model.user_Info.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;
import java.util.TreeSet;

public interface IMedicalNoteRepository extends JpaRepository<MedicalNote, Long> {
    Set<MedicalNote> findAllByUser(User user);
    MedicalNote findById(long id);
    Set<MedicalNote> findAllByOrderByDateAsc();

    @Query("SELECT m FROM MedicalNote m JOIN m.user u WHERE u.id=?1")
    TreeSet<MedicalNote> findAllByUserId(int id);
}
