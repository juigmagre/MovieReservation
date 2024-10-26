package com.jimg.myalbatross.modules.user.application.service;

import com.jimg.myalbatross.modules.user.application.dto.UserModifyRequest;
import com.jimg.myalbatross.modules.user.domain.entity.User;
import com.jimg.myalbatross.modules.user.domain.repository.UserRepository;
import com.jimg.myalbatross.modules.user.domain.vo.UserBirth;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserModifier {
    private final UserRepository userRepository;

    public void modify(UUID id, UserModifyRequest request) {
        log.info("Modifying user {} with mail: {}, username: {} and birthdate: {}", id, request.getMail(), request.getUsername(), request.getBirthDate());
        User user = userRepository.findById(id).orElseThrow(() -> new MyalbatrossException(MyalbatrossError.USER_NOT_EXISTS));
        userRepository.findByMail(request.getMail()).ifPresent(existingUser -> {
            if (!existingUser.equals(user)) {
                throw new MyalbatrossException(MyalbatrossError.MAIL_ALREADY_EXISTS);
            }
        });
        UserBirth birth = new UserBirth(request.getBirthDate());
        user.setMail(request.getMail());
        user.setBirthDate(birth);
        user.setUsername(request.getUsername());

        userRepository.save(user);
    }
}
