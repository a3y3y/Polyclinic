package by.it_academy.polyclinic.model.patient_info;

import by.it_academy.polyclinic.model.user_Info.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
@Table(name = "medical_notes")
public class MedicalNote implements Comparable<MedicalNote>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;
    private String type;
    private String name;
    private String illness;
    private String date;
    private String description;

    @Transient
    private String passport;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
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

    @Override
    public String toString() {
        return "MedicalNote{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", illness='" + illness + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", passport='" + passport + '\'' +
                '}';
    }
}
