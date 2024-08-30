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
import com.elite.mapper.user.UserSettingMapper;
import com.elite.mapper.user.UsersMapper;
import com.elite.model.LoginReqModel;
import com.elite.model.user.*;
import com.elite.repository.config.CodeRepository;
import com.elite.repository.config.CountryRepository;
import com.elite.repository.user.RoleRepository;
import com.elite.repository.user.UserRepository;
import com.elite.repository.user.UserSettingRepository;
import com.elite.service.config.MailService;
import com.elite.service.config.QRService;
import com.elite.service.user.UserService;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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

    private final QRService qrService;

    private final TemplateEngine templateEngine;

    private final ServletContext servletContext;

    private final UserSettingMapper userSettingMapper;

    public UserServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService,
                           UserRepository userRepository, RoleRepository roleRepository,
                           CodeRepository codeRepository, CountryRepository countryRepository,
                           UserSettingRepository userSettingRepository, AuthUserStore authUserStore,
                           UsersMapper usersMapper, CountryMapper countryMapper, CodeMapper codeMapper,
                           MailService mailService, QRService qrService, TemplateEngine templateEngine, ServletContext servletContext, UserSettingMapper userSettingMapper) {
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
        this.qrService = qrService;
        this.templateEngine = templateEngine;
        this.servletContext = servletContext;
        this.userSettingMapper = userSettingMapper;
    }

    @Override
    public String login(LoginReqModel loginReqModel) {
        log.info(MessageResource.getMessage(ESLog.ES_006), loginReqModel.getUsername());
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginReqModel.getUsername(), loginReqModel.getPassword()));
        if (authentication.isAuthenticated()) {
            log.info(MessageResource.getMessage(ESLog.ES_007), loginReqModel.getUsername());
            return jwtService.generateToken(authentication);
        } else {
            throw new ServiceException(MessageResource.getMessage(ESFault.ES_002));
        }
    }

    @Override
    public UserModel registration(UserReqModel userReqModel) {
        log.info(MessageResource.getMessage(ESLog.ES_008), userReqModel.getUsername());
        if (null != userRepository
                .findByUsername(userReqModel.getUsername())
                .orElse(null)) {
            throw new ServiceException(MessageResource.getMessage(ESFault.ES_005));
        }

        if (null != userRepository
                .findByEmail(userReqModel.getEmail())
                .orElse(null)) {
            throw new ServiceException(MessageResource.getMessage(ESFault.ES_004));
        }

        User user = new User();
        convertUserReqModelToUser(userReqModel, user);
        user.setUserRoles(new HashSet<>(roleRepository.findAllByNameIn(Collections.singletonList(RoleType.USER))));
        user = userRepository.save(user);
        log.info(MessageResource.getMessage(ESLog.ES_009), userReqModel.getUsername());
        createUserSetting(user.getId(), user.getUsername());
        sendWelcomeUserRegisterEmail(user.getEmail(), user.getUsername());
        return convertUserToUserModel(user);
    }

    @Override
    public UserModel getUserDetails() {
        log.info(MessageResource.getMessage(ESLog.ES_013));
        User user = userRepository
                .findByUsername(authUserStore.getLoggedInUser())
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_003)));
        log.info(MessageResource.getMessage(ESLog.ES_014));
        return convertUserToUserModel(user);
    }

    @Override
    public UserModel getUserDetailById(Long id) {
        log.info(MessageResource.getMessage(ESLog.ES_013));
        User user = userRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_003)));
        log.info(MessageResource.getMessage(ESLog.ES_014));
        return convertUserToUserModel(user);
    }

    @Override
    public UserModel updateUserDetail(Long id, UserReqModel userReqModel) {
        log.info(MessageResource.getMessage(ESLog.ES_033));
        User user = userRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_003)));
        convertUserReqModelToUser(userReqModel, user);
        User updatedUser = userRepository.save(user);
        log.info(MessageResource.getMessage(ESLog.ES_034));
        return convertUserToUserModel(updatedUser);
    }

    @Override
    public UserModel patchUserDetail(Long id, UserPatchReqModel userPatchReqModel) {
        log.info(MessageResource.getMessage(ESLog.ES_033));
        User user = userRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_003)));
        convertUserPatchReqModelToUser(userPatchReqModel, user);
        User updatedUser = userRepository.save(user);
        log.info(MessageResource.getMessage(ESLog.ES_034));
        return convertUserToUserModel(updatedUser);
    }

    @Override
    public UserModel patchUserPassword(Long id, UserPasswordPatchReqModel userPasswordPatchReqModel) {
        log.info(MessageResource.getMessage(ESLog.ES_033));
        User user = userRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_003)));
        if (BCrypt.checkpw(userPasswordPatchReqModel.getOldPassword(), user.getPassword())) {
            user.setPassword(BCrypt.hashpw(userPasswordPatchReqModel.getPassword(), BCrypt.gensalt()));
            user = userRepository.save(user);
            return convertUserToUserModel(user);
        }
        throw new ServiceException(MessageResource.getMessage(ESFault.ES_002));
    }

    @Override
    public Boolean deleteUserDetail(Long id) {
        log.info(MessageResource.getMessage(ESLog.ES_033));
        User user = userRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_003)));
        userSettingRepository.findByUserId(user.getId()).ifPresent(userSettingRepository::delete);
        userRepository.delete(user);
        return true;
    }

    @Override
    public UserSettingModel getUserSettings() {
        User user = userRepository
                .findByUsername(authUserStore.getLoggedInUser())
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_003)));
        UserSetting userSetting = userSettingRepository
                .findByUserId(user.getId())
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_003)));
        return userSettingMapper.toUserSettingDetail(userSetting);
    }

    @Override
    public UserSettingModel patchUserSettings(Long id, UserPatchSettingReqModel userPatchSettingReqModel) {
        UserSetting userSetting = userSettingRepository
                .findByUserId(id)
                .orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_003)));
        if (userPatchSettingReqModel.getRefreshCredentials()) {
            userSetting.setUid(authUserStore.generateRandomSecretKey(8));
            userSetting.setSecret(authUserStore.generateRandomSecretKey(32));
            userSetting.setToken(authUserStore.generateRandomSecretKey(32));
        }
        userSetting.setNotification(userPatchSettingReqModel.getNotification());
        userSetting.setEmail(userPatchSettingReqModel.getEmail());
        userSetting.setNightMode(userPatchSettingReqModel.getNightMode());
        userSetting = userSettingRepository.save(userSetting);
        return userSettingMapper.toUserSettingDetail(userSetting);
    }

    @Override
    public ResponseEntity<?> getUserDetailsPdf() {
        byte[] bytes = null;
        try {
            UserModel user = getUserDetails();
            Context context = new Context();
            context.setVariable("user", user);
            String orderHtml = templateEngine.process("user-detail", context);
            ByteArrayOutputStream target = new ByteArrayOutputStream();
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setBaseUri(servletContext.getContextPath());
            HtmlConverter.convertToPdf(orderHtml, target, converterProperties);
            bytes = target.toByteArray();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=user-detail.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }

    @Override
    public ResponseEntity<?> getUserDetailsQR() {
        UserModel userModel = getUserDetails();
        HashMap<String, String> qrMap = new HashMap<>();
        qrMap.put("name", userModel.getFirstName() + ' ' + userModel.getLastName());
        qrMap.put("username", userModel.getUsername());
        qrMap.put("email", userModel.getEmail());
        qrMap.put("address", userModel.getAddress());
        qrMap.put("phone", "+" + userModel.getCountry().getPhoneCode() + "-" + userModel.getPhone());
        qrMap.put("country", userModel.getCountry().getNiceName());
        byte[] qrCode = qrService.generateQRCode(qrMap, 320, 200);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(qrCode.length);
        return ResponseEntity.ok().headers(headers).body(qrCode);
    }

    @Override
    public Page<UserModel> searchUserDetails(String searchTerm, int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<User> users = userRepository.findByUsernameContainingIgnoreCaseOrderByUpdatedDateDesc(searchTerm, pageRequest);
        List<UserModel> userModels = users.getContent().stream().map(this::convertUserToUserModel).toList();
        return new PageImpl<>(userModels, pageRequest, users.getTotalElements());
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
        mailService.sendUserRegisterWelcomeMail(email, "New user welcome !!!", username);
    }

    private void convertUserPatchReqModelToUser(UserPatchReqModel userPatchReqModel, User user) {
        user.setUsername(userPatchReqModel.getUsername());
        user.setEmail(userPatchReqModel.getEmail());
        user.setFirstName(userPatchReqModel.getFirstName());
        user.setLastName(userPatchReqModel.getLastName());
        user.setAddress(userPatchReqModel.getAddress());
        user.setPhone(userPatchReqModel.getPhone());
        Code gender = codeRepository
                .findById(userPatchReqModel.getGenderId()).orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_007)));
        Country country = countryRepository
                .findById(userPatchReqModel.getCountryId()).orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_008)));
        user.setGender(gender.getId());
        user.setCountry(country.getId());
    }

    private void convertUserReqModelToUser(UserReqModel userReqModel, User user) {
        user.setUsername(userReqModel.getUsername());
        user.setEmail(userReqModel.getEmail());
        user.setPassword(BCrypt.hashpw(userReqModel.getPassword(), BCrypt.gensalt()));
        user.setFirstName(userReqModel.getFirstName());
        user.setLastName(userReqModel.getLastName());
        user.setAddress(userReqModel.getAddress());
        user.setPhone(userReqModel.getPhone());
        Code gender = codeRepository
                .findById(userReqModel.getGenderId()).orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_007)));
        Country country = countryRepository
                .findById(userReqModel.getCountryId()).orElseThrow(() ->
                        new NotFoundException(MessageResource.getMessage(ESFault.ES_008)));
        user.setGender(gender.getId());
        user.setCountry(country.getId());
    }

    private UserModel convertUserToUserModel(User user) {
        UserModel userModel = usersMapper.toUserDetail(user);
        userModel.setId(user.getId());
        userModel.setCountry(
                countryMapper.convertCountrytoCountryModel(
                        countryRepository.findById(user.getCountry()).orElse(null)));
        userModel.setGender(
                codeMapper.convertCodeToCodeModel(
                        codeRepository.findById(user.getGender()).orElse(null)));
        return userModel;
    }
}
