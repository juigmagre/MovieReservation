package com.jimg.myalbatross.modules.user.application.service;

import com.jimg.myalbatross.modules.user.application.dto.UserResponse;
import com.jimg.myalbatross.modules.user.application.mapper.UserMapper;
import com.jimg.myalbatross.modules.user.domain.entity.User;
import com.jimg.myalbatross.modules.user.domain.repository.UserRepository;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserFinder {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse findById(UUID id) {
        log.info("Finding user by id: {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new MyalbatrossException(MyalbatrossError.USER_NOT_EXISTS));
        return userMapper.toUserResponse(user);
    }
}
