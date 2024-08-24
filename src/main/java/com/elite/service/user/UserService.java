package com.elite.service.user;

import com.elite.model.Login;
import com.elite.model.user.UserDetail;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface UserService {

    String login(Login login);

    boolean registration(UserDetail userDetail);

    UserDetail getUserDetail();

    UserDetail getUserDetail(Long id);

    Page<UserDetail> searchUserDetail(String searchTerm, int pageIndex, int pageSize);

    UserDetail updateUserDetail(Long id, UserDetail userDetail);

    ResponseEntity<?> downloadUserDetail();

}
