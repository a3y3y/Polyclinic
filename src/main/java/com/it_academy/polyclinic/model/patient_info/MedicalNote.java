package com.it_academy.polyclinic.model.patient_info;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.it_academy.polyclinic.model.user_Info.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "medical_notes")
public class MedicalNote implements Comparable<MedicalNote>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String type;
    private String name;
    private String illness;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public int compareTo(MedicalNote o) {
        return this.date.compareTo(o.getDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalNote that = (MedicalNote) o;
        return id == that.id && Objects.equals(type, that.type) && Objects.equals(name, that.name) && Objects.equals(illness, that.illness) && Objects.equals(date, that.date) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, name, illness, date, description);
    }
}
