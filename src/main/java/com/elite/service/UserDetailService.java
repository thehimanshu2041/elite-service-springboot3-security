package com.elite.service;

import com.elite.core.exception.ESFault;
import com.elite.core.exception.exceptions.NotFoundException;
import com.elite.core.factory.MessageResource;
import com.elite.core.log.ESLog;
import com.elite.core.security.AuthUser;
import com.elite.entity.user.User;
import com.elite.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(MessageResource.getMessage(ESLog.ES_004), username);
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_003)));
        log.info(MessageResource.getMessage(ESLog.ES_005), username);
        return new AuthUser(user);
    }
}
