package com.elite.core.security;

import com.elite.constants.EliteType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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
            e.printStackTrace();
            return EliteType.SYSTEM_ANONYMOUS.getCode();
        }
    }
}
