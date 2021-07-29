package by.it_academy.polyclinic.model.patient_info;

import by.it_academy.polyclinic.model.user_Info.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "medical_cards")
public class MedicalCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private boolean healthful;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "medicalCard")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "medicalCard")
    private Set<MedicalNote> notes;

    public void addNote(MedicalNote note){
        this.notes.add(note);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isHealthful() {
        return healthful;
    }

    public void setHealthful(boolean healthful) {
        this.healthful = healthful;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<MedicalNote> getNotes() {
        return notes;
    }

    public void setNotes(Set<MedicalNote> notes) {
        this.notes = notes;
    }
}
