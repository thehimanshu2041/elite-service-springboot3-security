package com.elite.repository.config;

import com.elite.entity.config.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code, Long> {

    Optional<Code> findById(Long id);
}
