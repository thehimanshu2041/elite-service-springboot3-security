package com.elite.repository.config;

import com.elite.entity.config.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findById(Long id);

    Page<Country> findByNiceNameContainingIgnoreCase(String searchTerm, Pageable pageable);

    Optional<Country> findByIsp(String code);
}
