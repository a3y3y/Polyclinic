package by.it_academy.polyclinic.model.patient_info;

import by.it_academy.polyclinic.model.user_Info.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tickets")
public class Ticket implements Comparable<Ticket>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int number;
    private Date date;
    @Column(name = "office_number")
    private int officeNumber;

    @ManyToOne
    @JoinColumn(name = "patient")
    private User patient;

    @ManyToOne
    @JoinColumn(name = "doctor")
    private User doctor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(int officeNumber) {
        this.officeNumber = officeNumber;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    @Override
    public int compareTo(Ticket o) {
        return date.compareTo(o.getDate());
    }
}
