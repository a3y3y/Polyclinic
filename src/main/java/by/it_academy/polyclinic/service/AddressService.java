package by.it_academy.polyclinic.service;

import by.it_academy.polyclinic.model.user_Info.Address;
import by.it_academy.polyclinic.model.user_Info.Passport;
import by.it_academy.polyclinic.model.user_Info.User;
import by.it_academy.polyclinic.service.api.IAddressService;
import by.it_academy.polyclinic.storage.IAddressRepository;

import java.util.List;

public class AddressService implements IAddressService {

    IAddressRepository addressRepository;

    public AddressService(IAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void addAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(int id) {
        return addressRepository.findById(id);
    }

    @Override
    public boolean update(Address address, int id) {
        Address addressNew = addressRepository.findById(id);
        addressNew.setIndex(address.getIndex());
        addressNew.setApartmentNumber(address.getApartmentNumber());
        addressNew.setStreet(address.getStreet());
        addressNew.setHouseNumber(address.getHouseNumber());
        addressNew.setCity(address.getCity());
        addressNew.setRegion(addressNew.getRegion());
        addressNew.setCountry(address.getCountry());
        addressNew.setId(address.getId());
        Address addressSaved = addressRepository.save(addressNew);
        if(addressSaved.equals(addressNew)){
            return true;
        } else return false;
    }

    @Override
    public boolean delete(int id) {
        Address address = addressRepository.findById(id);
        if(address == null){
            return false;
        } else {
            addressRepository.delete(address);
            return true;
        }
    }
}
