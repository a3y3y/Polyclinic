package by.it_academy.polyclinic.model.doctor_info;

import by.it_academy.polyclinic.model.user_Info.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

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
    private String experience;
    private int rating;

    @OneToOne(mappedBy = "doctor")
    @JsonIgnore
    private User doctor;

    @Transient
    private int userId;


    @ManyToMany(mappedBy = "doctorInfo")
    @JsonIgnore
    private Set<Specialization> specializations = new HashSet<>();


    @ManyToMany(mappedBy = "doctorInfo")
    @JsonIgnore
    private Set<Department> departments = new HashSet<>();

    public DoctorInfo() {
    }

    public DoctorInfo(String lastPlaceOfWork, String lastPosition, String education, String experience, byte rating, User doctor, int userId) {
        this.lastPlaceOfWork = lastPlaceOfWork;
        this.lastPosition = lastPosition;
        this.education = education;
        this.experience = experience;
        this.rating = rating;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public Set<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<Specialization> specializations) {
        this.specializations = specializations;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return "DoctorInfo{" +
                "id=" + id +
                ", lastPlaceOfWork='" + lastPlaceOfWork + '\'' +
                ", lastPosition='" + lastPosition + '\'' +
                ", education='" + education + '\'' +
                ", experience='" + experience + '\'' +
                ", rating=" + rating +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorInfo that = (DoctorInfo) o;
        return id == that.id && rating == that.rating && Objects.equals(lastPlaceOfWork, that.lastPlaceOfWork) && Objects.equals(lastPosition, that.lastPosition) && Objects.equals(education, that.education) && Objects.equals(experience, that.experience);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastPlaceOfWork, lastPosition, education, experience, rating);
    }
}
