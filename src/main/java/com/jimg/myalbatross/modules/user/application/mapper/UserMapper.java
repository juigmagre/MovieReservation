package com.jimg.myalbatross.modules.user.application.mapper;

import com.jimg.myalbatross.modules.user.application.dto.UserResponse;
import com.jimg.myalbatross.modules.user.domain.entity.User;
import com.jimg.myalbatross.modules.user.domain.vo.UserBirth;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserResponse toUserResponse(User user);

    User toUser(UserResponse userResponse);

    default UserBirth map(LocalDateTime birthDate) {
        return nonNull(birthDate) ? new UserBirth(birthDate) : null;
    }
}
