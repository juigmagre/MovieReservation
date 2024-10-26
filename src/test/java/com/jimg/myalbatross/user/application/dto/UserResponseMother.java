package com.jimg.myalbatross.user.application.dto;

import com.jimg.myalbatross.modules.user.application.dto.UserResponse;
import com.jimg.myalbatross.modules.user.domain.entity.User;

import static java.util.Objects.isNull;

public class UserResponseMother {
    public static UserResponse withUser(User user) {
        if (isNull(user)) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setMail(user.getMail());
        userResponse.setUsername(user.getUsername());
        userResponse.setBirthDate(user.getBirthDate());

        return userResponse;
    }
}
