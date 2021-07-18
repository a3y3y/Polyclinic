package by.it_academy.polyclinic.model.user_Info;

import by.it_academy.polyclinic.model.doctor_info.DoctorInfo;
import by.it_academy.polyclinic.model.patient_info.MedicalCard;
import by.it_academy.polyclinic.model.patient_info.Ticket;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Column(name = "phone_number")
    private String phoneNumber;


    @Column(unique = true, nullable = false)
    private String eMail;

    @Column(nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "passport_id")
    private Passport passport;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "patient")
    private Set<Ticket> tickets;

    @OneToMany(mappedBy = "doctor")
    private Set<Ticket> ticketsForDoctor;

    @OneToOne
    @JoinColumn(name = "doctor_info_id")
    private DoctorInfo doctor;

    @OneToOne
    @JoinColumn(name = "medical_card_id")
    private MedicalCard medicalCard;

    private boolean active;

    public MedicalCard getMedicalCard() {
        return medicalCard;
    }

    public void setMedicalCard(MedicalCard medicalCard) {
        this.medicalCard = medicalCard;
    }

    public DoctorInfo getDoctor() {
        return doctor;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDoctor(DoctorInfo doctor) {
        this.doctor = doctor;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Set<Ticket> getTicketsForDoctor() {
        return ticketsForDoctor;
    }

    public void setTicketsForDoctor(Set<Ticket> ticketsForDoctor) {
        this.ticketsForDoctor = ticketsForDoctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getUsername() {
        return eMail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public Set<Role> getRoles()
    {
        return roles;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isActive() == user.isActive() &&
                Objects.equals(getId(), user.getId()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getRoles(), user.getRoles());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", roles=" + roles +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", eMail='" + eMail + '\'' +
                ", password='" + password + '\'' +
                ", passport=" + passport +
                ", address=" + address +
                ", tickets=" + tickets +
                ", ticketsForDoctor=" + ticketsForDoctor +
                ", doctor=" + doctor +
                ", medicalCard=" + medicalCard +
                ", active=" + active +
                '}';
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getPhoneNumber(), getPassword(), geteMail(), isActive()
                , getPassport(), getAddress(), getRoles(), getTickets(), getTicketsForDoctor(), getDoctor()
        ,getMedicalCard());
    }
}
