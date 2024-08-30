package com.elite.repository.config;

import com.elite.entity.user.IpAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpAddressRepository extends JpaRepository<IpAddress, Long> {

}
