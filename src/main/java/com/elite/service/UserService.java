package com.elite.service;

import com.elite.model.Login;
import com.elite.model.UserDetail;

public interface UserService {

    String login(Login login);

    void registration(UserDetail userDetail);

    UserDetail getUserDetail();

}
