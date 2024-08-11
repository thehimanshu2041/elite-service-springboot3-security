package com.elite.mapper.user;

import com.elite.entity.user.User;
import com.elite.model.user.UserDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "gender", ignore = true)
    @Mapping(target = "country", ignore = true)
    UserDetail toUserDetail(User user);

}
