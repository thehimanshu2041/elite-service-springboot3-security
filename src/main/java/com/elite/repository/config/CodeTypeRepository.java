package com.elite.repository.config;

import com.elite.entity.config.CodeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeTypeRepository extends JpaRepository<CodeType, Long> {

    Optional<CodeType> findById(Long id);

    Optional<CodeType> findByCode(String code);
}
