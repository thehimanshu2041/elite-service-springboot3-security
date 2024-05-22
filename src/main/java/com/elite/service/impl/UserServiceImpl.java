package com.elite.service.impl;

import com.elite.core.exception.ESFault;
import com.elite.core.exception.exceptions.NotFoundException;
import com.elite.core.exception.exceptions.ServiceException;
import com.elite.core.factory.MessageResource;
import com.elite.core.security.AuthUserStore;
import com.elite.core.security.JwtService;
import com.elite.core.security.RoleType;
import com.elite.entity.User;
import com.elite.mapper.UserMapper;
import com.elite.model.Login;
import com.elite.model.UserDetail;
import com.elite.repository.RoleRepository;
import com.elite.repository.UserRepository;
import com.elite.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final AuthUserStore authUserStore;

    private final UserMapper userMapper;

    public UserServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService, UserRepository userRepository, RoleRepository roleRepository, AuthUserStore authUserStore, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authUserStore = authUserStore;
        this.userMapper = userMapper;
    }

    @Override
    public String login(Login login) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(login.getUsername());
        } else {
            throw new ServiceException(MessageResource.getMessage(ESFault.ES_002));
        }
    }

    @Override
    public void registration(UserDetail userDetail) {
        if (null != userRepository
                .findByUsername(userDetail.getUsername())
                .orElse(null)) {
            throw new ServiceException(MessageResource.getMessage(ESFault.ES_005));
        }

        if (null != userRepository
                .findByEmail(userDetail.getEmail())
                .orElse(null)) {
            throw new ServiceException(MessageResource.getMessage(ESFault.ES_004));
        }

        User user = new User();
        user.setFirstName(userDetail.getFirstName());
        user.setLastName(userDetail.getLastName());
        user.setUsername(userDetail.getUsername());
        user.setEmail(userDetail.getEmail());
        user.setPassword(BCrypt.hashpw(userDetail.getPassword(), BCrypt.gensalt()));
        user.setUserRoles(roleRepository.findAllByNameIn(Collections.singletonList(RoleType.USER)));
        userRepository.save(user);
    }

    @Override
    public UserDetail getUserDetail() {
        User user = userRepository
                .findByUsername(authUserStore.getLoggedInUser())
                .orElse(null);
        if (null == user) {
            throw new NotFoundException(MessageResource.getMessage(ESFault.ES_003));
        }
        return userMapper.toUserDetail(user);
    }
}
