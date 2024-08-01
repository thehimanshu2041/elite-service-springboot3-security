package com.elite.mapper.user;

import com.elite.entity.user.User;
import com.elite.model.user.UserDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        GenderTypeMapper.class
})
public interface UsersMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "gender.genderType", source = "user.gender.codeType")
    UserDetail toUserDetail(User user);
}
