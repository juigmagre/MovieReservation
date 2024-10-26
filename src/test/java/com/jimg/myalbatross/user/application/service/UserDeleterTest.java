package com.jimg.myalbatross.user.application.service;

import com.jimg.myalbatross.modules.user.application.service.UserDeleter;
import com.jimg.myalbatross.modules.user.domain.entity.User;
import com.jimg.myalbatross.modules.user.domain.repository.UserRepository;
import com.jimg.myalbatross.shared.application.UnitTestCase;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import com.jimg.myalbatross.user.domain.entity.UserMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserDeleterTest extends UnitTestCase {
    @Mock
    private UserRepository userRepository;

    private UserDeleter userDeleter;

    @BeforeEach
    public void setUp() {
        userDeleter = new UserDeleter(userRepository);
    }

    @Test
    public void shouldDeleteUser() {
        User user = UserMother.random();

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        assertDoesNotThrow(() -> userDeleter.delete(user.getId()));
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void shouldThrowExceptionWhenUserNotFound() {
        User user = UserMother.random();

        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        MyalbatrossException actualException = assertThrows(MyalbatrossException.class, () -> userDeleter.delete(user.getId()));
        verify(userRepository, never()).delete(any(User.class));
        assertMyalbatrossException(MyalbatrossError.USER_NOT_EXISTS, actualException);
    }
}
