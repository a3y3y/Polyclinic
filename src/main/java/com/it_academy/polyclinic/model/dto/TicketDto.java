package com.it_academy.polyclinic.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalTime;

public class TicketDto implements Comparable<TicketDto>{
    private long id;
    private int number;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Column(name = "office_number")
    private String officeNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime time;
    private boolean available;
    private String lastName;
    private String firstName;

    public TicketDto() {
    }

    @Override
    public String toString() {
        return "TicketDto{" +
                "id=" + id +
                ", number=" + number +
                ", date=" + date +
                ", officeNumber='" + officeNumber + '\'' +
                ", time=" + time +
                ", available=" + available +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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

    public TicketDto(long id, int number, LocalDate date, String officeNumber, LocalTime time, boolean available, String lastName, String firstName) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.officeNumber = officeNumber;
        this.time = time;
        this.available = available;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    @Override
    public int compareTo(TicketDto o) {
        int result = this.date.compareTo(o.getDate());

        if (result == 0) {
            result = this.time.compareTo(o.getTime());
        }
        return result;
    }
}