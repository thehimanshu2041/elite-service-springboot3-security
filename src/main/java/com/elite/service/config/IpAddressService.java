package com.elite.service.config;

import jakarta.servlet.http.HttpServletRequest;

public interface IpAddressService {

    void trackUserRequest(HttpServletRequest httpServletRequest);
}
