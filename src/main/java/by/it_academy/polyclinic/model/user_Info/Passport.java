package by.it_academy.polyclinic.model.user_Info;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "passports")
public class Passport implements Comparable<Passport> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    private String patronymic;
    @Column(name = "code_of_issuing_state")
    private String codeOfIssuingState;
    private String number;
    @Column(name = "personal_id")
    private String personalId;
    private String nationality;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    private String sex;
    @Column(name = "issue_date")
    private String issueDate;
    @Column(name = "expire_date")
    private String expireDate;

    @Transient
    private int userId;

    @OneToOne(mappedBy = "passport")
    @JsonIgnore
    private User user;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getCodeOfIssuingState() {
        return codeOfIssuingState;
    }

    public void setCodeOfIssuingState(String codeOfIssuingState) {
        this.codeOfIssuingState = codeOfIssuingState;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public int compareTo(Passport o) {
        return this.lastName.compareTo(o.getLastName());
    }

    @JsonIgnore
    public String getPassportInfo() {
        return lastName + " " + firstName + " " + patronymic + " " + number;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "Id=" + Id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", codeOfIssuingState='" + codeOfIssuingState + '\'' +
                ", number='" + number + '\'' +
                ", personalId='" + personalId + '\'' +
                ", nationality='" + nationality + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", sex='" + sex + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", expireDate='" + expireDate + '\'' +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o)
        {
            if(this == o) return true;
            if(o == null || getClass() != o.getClass()) return false;
            Passport passport = (Passport) o;
            return  Objects.equals(getId(), passport.getId()) &&
                    Objects.equals(getCodeOfIssuingState(), passport.getCodeOfIssuingState()) &&
                    Objects.equals(getDateOfBirth(), passport.getDateOfBirth()) &&
                    Objects.equals(getExpireDate(), passport.getExpireDate()) &&
                    Objects.equals(getFirstName(), passport.getFirstName()) &&
                    Objects.equals(getIssueDate(), passport.getIssueDate()) &&
                    Objects.equals(getLastName(), passport.getLastName()) &&
                    Objects.equals(getNationality(), passport.getNationality()) &&
                    Objects.equals(getNumber(), passport.getNumber()) &&
                    Objects.equals(getPatronymic(), passport.getPatronymic()) &&
                    Objects.equals(getPersonalId(), passport.getPersonalId()) &&
                    Objects.equals(getSex(), passport.getSex());
        }

}
