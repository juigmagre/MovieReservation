package com.jimg.myalbatross.user.application.service;

import com.jimg.myalbatross.modules.user.application.dto.UserCreateRequest;
import com.jimg.myalbatross.modules.user.application.service.UserCreator;
import com.jimg.myalbatross.modules.user.domain.entity.User;
import com.jimg.myalbatross.modules.user.domain.repository.UserRepository;
import com.jimg.myalbatross.shared.application.UnitTestCase;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import com.jimg.myalbatross.user.application.dto.UserCreateRequestMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserCreatorTest extends UnitTestCase {
    @Mock
    private UserRepository userRepository;

    private UserCreator userCreator;

    @BeforeEach
    void setUp() {
        userCreator = new UserCreator(userRepository);
    }

    @Test
    void shouldCreateUser() {
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        UserCreateRequest userCreateRequest = UserCreateRequestMother.random();

        when(userRepository.existsById(userCreateRequest.getId())).thenReturn(false);

        assertDoesNotThrow(() -> userCreator.create(userCreateRequest));

        verify(userRepository, times(1)).existsById(userCreateRequest.getId());
        verify(userRepository, times(1)).save(userCaptor.capture());
        assertEquals(userCreateRequest, userCaptor.getValue());
    }

    @Test
    void shouldThrowMyalbatrossExceptionWhenUserExists() {
        UserCreateRequest userCreateRequest = UserCreateRequestMother.random();

        when(userRepository.existsById(userCreateRequest.getId())).thenReturn(true);

        MyalbatrossException myalbatrossException = assertThrows(MyalbatrossException.class, () -> userCreator.create(userCreateRequest));

        verify(userRepository, times(1)).existsById(userCreateRequest.getId());
        verify(userRepository, never()).save(any());
        assertMyalbatrossException(MyalbatrossError.USER_ALREADY_EXISTS, myalbatrossException);
    }

    @Test
    void shouldThrowMyalbatrossExceptionWhenMailExists() {
        UserCreateRequest userCreateRequest = UserCreateRequestMother.random();

        when(userRepository.existsById(userCreateRequest.getId())).thenReturn(false);
        when(userRepository.existsByMail(userCreateRequest.getMail())).thenReturn(true);

        MyalbatrossException myalbatrossException = assertThrows(MyalbatrossException.class, () -> userCreator.create(userCreateRequest));

        verify(userRepository, times(1)).existsById(userCreateRequest.getId());
        verify(userRepository, times(1)).existsByMail(userCreateRequest.getMail());
        verify(userRepository, never()).save(any());
        assertMyalbatrossException(MyalbatrossError.MAIL_ALREADY_EXISTS, myalbatrossException);
    }

    @Test
    void shouldThrowMyalbatrossExceptionBirthDateIsNotCorrect() {
        UserCreateRequest userWithIncorrectBirthDate = UserCreateRequestMother.randomWithIncorrectBirthDate();

        when(userRepository.existsById(userWithIncorrectBirthDate.getId())).thenReturn(false);

        MyalbatrossException actualException = assertThrows(MyalbatrossException.class, () -> userCreator.create(userWithIncorrectBirthDate));
        verify(userRepository, never()).save(any());
        assertMyalbatrossException(MyalbatrossError.USER_AGE_NOT_CORRECT, actualException);
    }

    private void assertEquals(UserCreateRequest expected, User actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getBirthDate(), actual.getBirthDate());
        Assertions.assertEquals(expected.getMail(), actual.getMail());
        Assertions.assertEquals(expected.getUsername(), actual.getUsername());
    }
}
