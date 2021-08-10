package com.it_academy.polyclinic.storage;

import com.it_academy.polyclinic.model.user_Info.Address;
import com.it_academy.polyclinic.model.user_Info.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IAddressRepository extends JpaRepository<Address, Integer> {
    Address findById(int id);

    @Query("SELECT a FROM Address a WHERE ?1 MEMBER a.users")
    Address findByUser(User user);
}
