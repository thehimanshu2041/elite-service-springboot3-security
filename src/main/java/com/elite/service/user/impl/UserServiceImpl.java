package com.elite.service.user.impl;

import com.elite.core.exception.ESFault;
import com.elite.core.exception.exceptions.NotFoundException;
import com.elite.core.exception.exceptions.ServiceException;
import com.elite.core.factory.MessageResource;
import com.elite.core.log.ESLog;
import com.elite.core.security.AuthUserStore;
import com.elite.core.security.JwtService;
import com.elite.core.security.RoleType;
import com.elite.entity.config.Code;
import com.elite.entity.config.Country;
import com.elite.entity.user.User;
import com.elite.entity.user.UserSetting;
import com.elite.mapper.config.CodeMapper;
import com.elite.mapper.config.CountryMapper;
import com.elite.mapper.user.UsersMapper;
import com.elite.model.Login;
import com.elite.model.user.UserDetail;
import com.elite.repository.config.CodeRepository;
import com.elite.repository.config.CountryRepository;
import com.elite.repository.config.UserSettingRepository;
import com.elite.repository.user.RoleRepository;
import com.elite.repository.user.UserRepository;
import com.elite.service.config.MailService;
import com.elite.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final CodeRepository codeRepository;

    private final CountryRepository countryRepository;

    private final UserSettingRepository userSettingRepository;

    private final AuthUserStore authUserStore;

    private final UsersMapper usersMapper;

    private final CountryMapper countryMapper;

    private final CodeMapper codeMapper;


    private final MailService mailService;

    public UserServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService,
                           UserRepository userRepository, RoleRepository roleRepository,
                           CodeRepository codeRepository, CountryRepository countryRepository,
                           UserSettingRepository userSettingRepository, AuthUserStore authUserStore,
                           UsersMapper usersMapper, CountryMapper countryMapper, CodeMapper codeMapper,
                           MailService mailService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.codeRepository = codeRepository;
        this.countryRepository = countryRepository;
        this.userSettingRepository = userSettingRepository;
        this.authUserStore = authUserStore;
        this.usersMapper = usersMapper;
        this.countryMapper = countryMapper;
        this.codeMapper = codeMapper;
        this.mailService = mailService;
    }

    @Override
    public String login(Login login) {
        log.info(MessageResource.getMessage(ESLog.ES_006), login.getUsername());
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        if (authentication.isAuthenticated()) {
            log.info(MessageResource.getMessage(ESLog.ES_007), login.getUsername());
            return jwtService.generateToken(login.getUsername());
        } else {
            throw new ServiceException(MessageResource.getMessage(ESFault.ES_002));
        }
    }

    @Override
    public void registration(UserDetail userDetail) {
        log.info(MessageResource.getMessage(ESLog.ES_008), userDetail.getUsername());
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
        user.setUsername(userDetail.getUsername());
        user.setEmail(userDetail.getEmail());
        user.setPassword(BCrypt.hashpw(userDetail.getPassword(), BCrypt.gensalt()));
        user.setFirstName(userDetail.getFirstName());
        user.setLastName(userDetail.getLastName());
        user.setAddress(userDetail.getAddress());
        user.setPhone(userDetail.getPhone());

        Code gender = codeRepository
                .findById(userDetail.getGender().getId()).orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_007)));
        Country country = countryRepository
                .findById(userDetail.getCountry().getId()).orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_008)));

        user.setGender(gender.getId());
        user.setCountry(country.getId());
        user.setUserRoles(roleRepository.findAllByNameIn(Collections.singletonList(RoleType.USER)));
        user = userRepository.save(user);
        log.info(MessageResource.getMessage(ESLog.ES_009), userDetail.getUsername());
        createUserSetting(user.getId(), user.getUsername());
        sendWelcomeUserRegisterEmail(user.getEmail(), user.getUsername());
    }

    @Override
    public UserDetail getUserDetail() {
        log.info(MessageResource.getMessage(ESLog.ES_013));
        User user = userRepository
                .findByUsername(authUserStore.getLoggedInUser())
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_003)));
        UserDetail userDetail = usersMapper.toUserDetail(user);
        userDetail.setCountry(
                countryMapper.toUserDetailCountry(
                        countryRepository.findById(user.getCountry()).orElse(null)));
        userDetail.setGender(
                codeMapper.toUserDetailGender(
                        codeRepository.findById(user.getGender()).orElse(null)));
        log.info(MessageResource.getMessage(ESLog.ES_014));
        return userDetail;
    }

    private void createUserSetting(Long userId, String username) {
        try {
            log.info(MessageResource.getMessage(ESLog.ES_011), username);
            UserSetting userSetting = new UserSetting();
            userSetting.setUserId(userId);
            userSetting.setUid(authUserStore.generateRandomSecretKey(8));
            userSetting.setSecret(authUserStore.generateRandomSecretKey(32));
            userSetting.setToken(authUserStore.generateRandomSecretKey(32));
            userSetting.setNotification(false);
            userSetting.setEmail(false);
            userSetting.setNightMode(false);
            userSettingRepository.save(userSetting);
            log.info(MessageResource.getMessage(ESLog.ES_012));
        } catch (Exception e) {
            log.error(MessageResource.getMessage(ESFault.ES_011), e.getMessage());
        }
    }

    private void sendWelcomeUserRegisterEmail(String email, String username) {
        try {
            mailService.sendUserRegisterWelcomeMail(email, "New user welcome !!!", username);
        } catch (Exception e) {
            log.error(MessageResource.getMessage(ESFault.ES_006), username, e.getMessage());
        }
    }
}
