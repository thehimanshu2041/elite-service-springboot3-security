package com.elite.mapper;

import com.elite.entity.User;
import com.elite.model.UserDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserDetail toUserDetail(User user);
}
