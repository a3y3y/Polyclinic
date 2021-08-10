package com.it_academy.polyclinic.storage;

import com.it_academy.polyclinic.model.site_info.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDiseaseRepository extends JpaRepository<Disease, Integer> {
}
