package by.it_academy.polyclinic.storage;

import by.it_academy.polyclinic.model.user_Info.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, Integer> {
}
