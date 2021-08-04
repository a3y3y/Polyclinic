package by.it_academy.polyclinic.model.doctor_info;

import by.it_academy.polyclinic.model.user_Info.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private byte rating;

    @OneToOne(mappedBy = "doctor")
    private User doctor;

    @Transient
    private int userId;

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

    public DoctorInfo() {
    }

    public DoctorInfo(int id, String lastPlaceOfWork, String lastPosition, String education, String experience, byte rating, User doctor, int userId) {
        this.id = id;
        this.lastPlaceOfWork = lastPlaceOfWork;
        this.lastPosition = lastPosition;
        this.education = education;
        this.experience = experience;
        this.rating = rating;
        this.doctor = doctor;
        this.userId = userId;
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

    @Override
    public String toString() {
        return "DoctorInfo{" +
                "lastPlaceOfWork='" + lastPlaceOfWork + '\'' +
                ", lastPosition='" + lastPosition + '\'' +
                ", education='" + education + '\'' +
                ", experience=" + experience +
                ", rating=" + rating +
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
