package by.it_academy.polyclinic.model.doctor_info;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "specializations")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "specializations")
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
}
