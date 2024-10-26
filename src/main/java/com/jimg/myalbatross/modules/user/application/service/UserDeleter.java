package com.jimg.myalbatross.modules.user.application.service;

import com.jimg.myalbatross.modules.user.domain.entity.User;
import com.jimg.myalbatross.modules.user.domain.repository.UserRepository;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserDeleter {
    private final UserRepository userRepository;

    public void delete(UUID id) {
        log.info("Delete user with id {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new MyalbatrossException(MyalbatrossError.USER_NOT_EXISTS));
        if (user.hasReservations()) {
            throw new MyalbatrossException(MyalbatrossError.USER_HAS_RESERVATIONS);
        }
        userRepository.delete(user);
    }
}
