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

    @OneToOne(mappedBy = "medicalCard")
    private User user;

    @OneToMany(mappedBy = "medicalCard")
    private Set<MedicalProcedure> procedures;

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

    public Set<MedicalProcedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(Set<MedicalProcedure> procedures) {
        this.procedures = procedures;
    }
}
