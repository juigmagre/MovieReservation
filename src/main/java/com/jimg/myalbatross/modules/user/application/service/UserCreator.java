package com.jimg.myalbatross.modules.user.application.service;

import com.jimg.myalbatross.modules.user.application.dto.UserCreateRequest;
import com.jimg.myalbatross.modules.user.domain.entity.User;
import com.jimg.myalbatross.modules.user.domain.repository.UserRepository;
import com.jimg.myalbatross.modules.user.domain.vo.UserBirth;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserCreator {
    private final UserRepository userRepository;

    public void create(UserCreateRequest request) {
        log.info("Creating user with id: {}, mail: {}, username: {} and birthdate: {}", request.getId(), request.getMail(), request.getUsername(), request.getBirthDate());
        if (userRepository.existsById(request.getId())) {
            throw new MyalbatrossException(MyalbatrossError.USER_ALREADY_EXISTS);
        }
        if (userRepository.existsByMail(request.getMail())) {
            throw new MyalbatrossException(MyalbatrossError.MAIL_ALREADY_EXISTS);
        }
        UserBirth birth = new UserBirth(request.getBirthDate());
        User user = new User(request.getId(), request.getMail(), request.getUsername(), birth);

        userRepository.save(user);
    }
}
