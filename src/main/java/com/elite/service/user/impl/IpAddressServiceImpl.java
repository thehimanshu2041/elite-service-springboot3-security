package com.elite.service.user.impl;

import com.elite.core.factory.MessageResource;
import com.elite.core.log.ESLog;
import com.elite.core.security.AuthUserStore;
import com.elite.core.security.JwtService;
import com.elite.entity.user.IpAddress;
import com.elite.repository.config.IpAddressRepository;
import com.elite.service.user.IpAddressService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IpAddressServiceImpl implements IpAddressService {

    private final JwtService jwtService;

    private final AuthUserStore authUserStore;

    private final IpAddressRepository ipAddressRepository;

    public IpAddressServiceImpl(JwtService jwtService, AuthUserStore authUserStore, IpAddressRepository ipAddressRepository) {
        this.jwtService = jwtService;
        this.authUserStore = authUserStore;
        this.ipAddressRepository = ipAddressRepository;
    }

    @Override
    public void trackUserRequest(HttpServletRequest request) {
        log.info(MessageResource.getMessage(ESLog.ES_015));
        IpAddress ipAddress = new IpAddress();
        String token = jwtService.getValidToken(request.getHeader("Authorization"));
        ipAddress.setToken(token);
        ipAddress.setUsername(null != token ? jwtService.getUsernameFromToken(token) : null);
        ipAddress.setPort(Integer.toString(request.getServerPort()));
        ipAddress.setContextPath(request.getContextPath());
        ipAddress.setRequestPath(request.getRequestURI());
        ipAddress.setAddress(authUserStore.getIpAddress(request));
        ipAddress.setMethod(request.getMethod());
        ipAddress.setPostalCode(null);
        ipAddress.setLatitude(null);
        ipAddress.setLongitude(null);
        ipAddress.setCity(null);
        ipAddress.setState(null);
        ipAddress.setCountry(null);
        ipAddress.setMetroCode(null);
        ipAddress.setAreaCode(null);
        ipAddress.setNum(null);
        ipAddress.setUrl(null);
        ipAddress.setReferer(null);
        ipAddressRepository.save(ipAddress);
        log.info(MessageResource.getMessage(ESLog.ES_016));
    }
}
