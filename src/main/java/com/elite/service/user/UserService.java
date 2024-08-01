package com.elite.service.user;

import com.elite.model.Login;
import com.elite.model.user.UserDetail;

public interface UserService {

    String login(Login login);

    void registration(UserDetail userDetail);

    UserDetail getUserDetail();

}
