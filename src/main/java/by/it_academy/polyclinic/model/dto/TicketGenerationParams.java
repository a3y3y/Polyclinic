package by.it_academy.polyclinic.model.dto;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class TicketGenerationParams {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime breakStart;
    private LocalTime breakEnd;
    private String officeNumber;
    private int doctorId;
    private int durationOfAppointment;

    public TicketGenerationParams() {
    }

    public TicketGenerationParams(LocalDate startDate, LocalDate endDate, LocalTime startTime,
                                  LocalTime endTime, LocalTime breakStart, LocalTime breakEnd,
                                  String officeNumber, int doctorId, int durationOfAppointment) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.breakStart = breakStart;
        this.breakEnd = breakEnd;
        this.officeNumber = officeNumber;
        this.doctorId = doctorId;
        this.durationOfAppointment = durationOfAppointment;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getBreakStart() {
        return breakStart;
    }

    public void setBreakStart(LocalTime breakStart) {
        this.breakStart = breakStart;
    }

    public LocalTime getBreakEnd() {
        return breakEnd;
    }

    public void setBreakEnd(LocalTime breakEnd) {
        this.breakEnd = breakEnd;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getDurationOfAppointment() {
        return durationOfAppointment;
    }

    public void setDurationOfAppointment(int durationOfAppointment) {
        this.durationOfAppointment = durationOfAppointment;
    }

    @Override
    public String toString() {
        return "TicketGenerationParams{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", breakStart=" + breakStart +
                ", breakEnd=" + breakEnd +
                ", officeNumber=" + officeNumber +
                ", doctorId=" + doctorId +
                ", durationOfAppointment=" + durationOfAppointment +
                '}';
    }
}
