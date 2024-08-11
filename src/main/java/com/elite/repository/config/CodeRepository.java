package com.elite.repository.config;

import com.elite.entity.config.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code, Long> {

    Optional<Code> findById(Long id);

    Optional<Code> findByCode(String code);

    List<Code> findAllByCodeTypeId(Long id);

}
