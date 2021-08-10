package com.it_academy.polyclinic.model.dto;

import com.it_academy.polyclinic.model.user_Info.Address;
import com.it_academy.polyclinic.model.user_Info.Passport;
import com.it_academy.polyclinic.model.user_Info.User;

public class UserPassportAddressWrapper {
    private User user;
    private Passport passport;
    private Address address;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "UserPassportAddressWrapper{" +
                "user=" + user +
                ", passport=" + passport +
                ", address=" + address +
                '}';
    }
}
