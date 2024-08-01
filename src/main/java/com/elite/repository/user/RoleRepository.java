package com.elite.repository.user;

import com.elite.core.security.RoleType;
import com.elite.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAllByNameIn(List<RoleType> roles);
}
