package com.it_academy.polyclinic.storage;

import com.it_academy.polyclinic.model.site_info.LocalNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILocalNewsRepository extends JpaRepository<LocalNews, Integer> {
}
