package com.it_academy.polyclinic.model.dto;


import java.util.List;

public class DoctorDto {
    private int id;
    private String lastPlaceOfWork;
    private String lastPosition;
    private String education;
    private String experience;
    private int rating;
    private int userId;
    private List<Integer> specializations;
    private List<Integer> departments;

    public String getLastPlaceOfWork() {
        return lastPlaceOfWork;
    }

    public void setLastPlaceOfWork(String lastPlaceOfWork) {
        this.lastPlaceOfWork = lastPlaceOfWork;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Integer> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Integer> specializations) {
        this.specializations = specializations;
    }

    public List<Integer> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Integer> departments) {
        this.departments = departments;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", lastPlaceOfWork='" + lastPlaceOfWork + '\'' +
                ", lastPosition='" + lastPosition + '\'' +
                ", education='" + education + '\'' +
                ", experience='" + experience + '\'' +
                ", rating=" + rating +
                ", userId='" + userId + '\'' +
                ", specializations=" + specializations +
                ", departments=" + departments +
                '}';
    }
}
