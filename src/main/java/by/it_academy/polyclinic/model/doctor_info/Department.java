package by.it_academy.polyclinic.model.doctor_info;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(unique = true)
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(
            name = "department_doctor",
            joinColumns = { @JoinColumn(name = "doctor_info_id") },
            inverseJoinColumns = { @JoinColumn(name = "department_id") })
    @JsonIgnore
    private List<DoctorInfo> doctorInfo = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DoctorInfo> getDoctorInfo() {
        return doctorInfo;
    }

    public void setDoctorInfo(List<DoctorInfo> doctorInfo) {
        this.doctorInfo = doctorInfo;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id && name.equals(that.name) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
