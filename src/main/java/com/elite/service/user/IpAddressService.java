package com.elite.service.user;

import jakarta.servlet.http.HttpServletRequest;

public interface IpAddressService {

    void trackUserRequest(HttpServletRequest httpServletRequest);
}
