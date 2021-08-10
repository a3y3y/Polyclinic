package com.it_academy.polyclinic.model.dto;

public class UserDto {
    private int id;
    private String lastName;
    private String firstName;
    private String number;

    public UserDto(int id, String lastName, String firstName, String number) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPassportNumber() {
        return number;
    }

    public void setPassportNumber(String passportNumber) {
        this.number = passportNumber;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", passportNumber='" + number + '\'' +
                '}';
    }
}
