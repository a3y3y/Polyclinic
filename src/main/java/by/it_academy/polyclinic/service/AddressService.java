package by.it_academy.polyclinic.service;

import by.it_academy.polyclinic.model.user_Info.Address;
import by.it_academy.polyclinic.service.api.IAddressService;
import by.it_academy.polyclinic.storage.IAddressRepository;

public class AddressService implements IAddressService {

    IAddressRepository addressRepository;

    public AddressService(IAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void addAddress(Address address) {
        addressRepository.save(address);
    }
}
