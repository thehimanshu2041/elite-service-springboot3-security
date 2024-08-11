package com.elite.core.security;

import com.elite.constants.EliteType;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class AuthUserStore {

    public String getLoggedInUser() {
        try {
            String username;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
            return username;
        } catch (Exception e) {
            return EliteType.ANONYMOUS.getCode();
        }
    }

    public String getIpAddress(HttpServletRequest httpServletRequest) {
        String remoteAddr = null;
        if (httpServletRequest != null) {
            remoteAddr = httpServletRequest.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || remoteAddr.isEmpty()) {
                remoteAddr = httpServletRequest.getRemoteAddr();
            }
        }
        return remoteAddr;
    }

    public  String generateRandomSecretKey(int keyLength) {
        byte[] randomBytes = new byte[keyLength];
        new SecureRandom().nextBytes(randomBytes);
        return Base64.getEncoder().encodeToString(randomBytes);
    }
}
