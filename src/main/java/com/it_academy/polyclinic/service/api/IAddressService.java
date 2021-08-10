package com.it_academy.polyclinic.service.api;

import com.it_academy.polyclinic.model.user_Info.Address;
import com.it_academy.polyclinic.model.user_Info.User;

import java.util.List;

public interface IAddressService {
    void addAddress(Address address);
    List<Address> getAll();
    Address getAddressById(int id);
    boolean update(Address address, int id);
    boolean delete(int id);
    Address getByUser(User user);

}
