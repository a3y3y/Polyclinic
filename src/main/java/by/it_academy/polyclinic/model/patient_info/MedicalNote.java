package by.it_academy.polyclinic.model.patient_info;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "medical_services")
public class MedicalNote implements Comparable<MedicalNote>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Enumerated(EnumType.STRING)
    private MedicalNoteType type;
    private String name;
    private String illness;
    private String date;
    private String description;

    @ManyToOne
    @JoinColumn(name = "medical_card_id")
    private MedicalCard medicalCard;

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public MedicalCard getMedicalCard() {
        return medicalCard;
    }

    public void setMedicalCard(MedicalCard medicalCard) {
        this.medicalCard = medicalCard;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MedicalNoteType getType() {
        return type;
    }

    public void setType(MedicalNoteType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(MedicalNote o) {
       return this.date.compareTo(o.getDate());
    }
}
