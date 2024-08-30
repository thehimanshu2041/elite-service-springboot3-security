package com.elite.repository.config;

import com.elite.entity.config.Code;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code, Long> {

    Optional<Code> findById(Long id);

    Optional<Code> findByCode(String code);

    List<Code> findAllByCodeTypeIdOrderByUpdatedDateDesc(Long id);

    Page<Code> findByNameContainingIgnoreCaseOrderByUpdatedDateDesc(String searchTerm, Pageable pageable);

    Page<Code> findByCodeTypeIdAndNameContainingIgnoreCaseOrderByUpdatedDateDesc(Long id, String searchTerm, Pageable pageable);

}
