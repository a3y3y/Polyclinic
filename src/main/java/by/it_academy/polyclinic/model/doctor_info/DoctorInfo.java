package by.it_academy.polyclinic.model.doctor_info;

import by.it_academy.polyclinic.model.user_Info.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "doctor_info")
public class DoctorInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "last_place_of_work")
    private String lastPlaceOfWork;
    @Column(name = "last_position")
    private String lastPosition;
    private String education;
    private Date experience;
    private byte rating;

    @OneToOne(mappedBy = "doctor")
    private User doctor;

    @ManyToMany
    @JoinTable(
            name = "specialization_doctor",
            joinColumns = { @JoinColumn(name = "doctor_info_id") },
            inverseJoinColumns = { @JoinColumn(name = "specialization_id") })
    private List<Specialization> specializations = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "department_doctor",
            joinColumns = { @JoinColumn(name = "doctor_info_id") },
            inverseJoinColumns = { @JoinColumn(name = "department_id") })
    private List<Department> departments = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastPlaceOfWork() {
        return lastPlaceOfWork;
    }

    public void setLastPlaceOfWork(String lastPlaceOfWork) {
        this.lastPlaceOfWork = lastPlaceOfWork;
    }

    public String getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(String lastPosition) {
        this.lastPosition = lastPosition;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Date getExperience() {
        return experience;
    }

    public void setExperience(Date experience) {
        this.experience = experience;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}
