package com.elite.mapper.user;

import com.elite.entity.user.UserSetting;
import com.elite.model.user.UserSettingModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserSettingMapper {

    UserSettingModel toUserSettingDetail(UserSetting userSetting);
}
