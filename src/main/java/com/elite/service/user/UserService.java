package com.elite.service.user;

import com.elite.model.LoginReqModel;
import com.elite.model.user.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface UserService {

    String login(LoginReqModel loginReqModel);

    UserModel registration(UserReqModel userReqModel);

    UserModel getUserDetails();

    UserModel getUserDetailById(Long id);

    UserModel updateUserDetail(Long id, UserReqModel userReqModel);

    UserModel patchUserDetail(Long id, UserPatchReqModel userPatchReqModel);

    UserModel patchUserPassword(Long id, UserPasswordPatchReqModel userPasswordPatchReqModel);

    Boolean deleteUserDetail(Long id);

    UserSettingModel getUserSettings();

    UserSettingModel patchUserSettings(Long id, UserPatchSettingReqModel userPatchSettingReqModel);

    ResponseEntity<?> getUserDetailsPdf();

    ResponseEntity<?> getUserDetailsQR();

    Page<UserModel> searchUserDetails(String searchTerm, int pageIndex, int pageSize);

}
