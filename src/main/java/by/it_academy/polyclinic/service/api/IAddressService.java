package by.it_academy.polyclinic.service.api;

import by.it_academy.polyclinic.model.user_Info.Address;

import java.util.List;

public interface IAddressService {
    void addAddress(Address address);
    List<Address> getAll();
    Address getAddressById(int id);
    boolean update(Address address, int id);
    boolean delete(int id);

}
