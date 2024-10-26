package com.jimg.myalbatross.user.application.service;

import com.jimg.myalbatross.modules.user.application.dto.UserModifyRequest;
import com.jimg.myalbatross.modules.user.application.service.UserModifier;
import com.jimg.myalbatross.modules.user.domain.entity.User;
import com.jimg.myalbatross.modules.user.domain.repository.UserRepository;
import com.jimg.myalbatross.shared.application.UnitTestCase;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import com.jimg.myalbatross.user.application.dto.UserModifyRequestMother;
import com.jimg.myalbatross.user.domain.entity.UserMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserModifierTest extends UnitTestCase {
    @Mock
    private UserRepository userRepository;

    private UserModifier userModifier;

    @BeforeEach
    public void setUp() {
        userModifier = new UserModifier(userRepository);
    }

    @ParameterizedTest
    @MethodSource("correctParametersProvider")
    public void ShouldModifyUser(User originalUser, User otherUser, UserModifyRequest request) {
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        when(userRepository.findById(originalUser.getId())).thenReturn(Optional.of(originalUser));
        when(userRepository.findByMail(request.getMail())).thenReturn(Optional.ofNullable(otherUser));

        assertDoesNotThrow(() -> userModifier.modify(originalUser.getId(), request));

        verify(userRepository, times(1)).save(userCaptor.capture());
        verify(userRepository, times(1)).findById(originalUser.getId());
        verify(userRepository, times(1)).findByMail(request.getMail());
        assertEquals(request, userCaptor.getValue());
    }

    @Test
    public void ShouldThrowMyalbatrossExceptionWhenUserNotExists() {
        User user = UserMother.random();
        UserModifyRequest request = UserModifyRequestMother.random();

        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        MyalbatrossException expectedException = assertThrows(MyalbatrossException.class, () -> userModifier.modify(user.getId(), request));
        verify(userRepository, times(1)).findById(user.getId());
        verify(userRepository, never()).findByMail(any());
        verify(userRepository, never()).save(any());
        assertMyalbatrossException(MyalbatrossError.USER_NOT_EXISTS, expectedException);
    }

    @Test
    public void ShouldThrowMyalbatrossExceptionWhenMailExists() {
        User user = UserMother.random();
        UserModifyRequest request = UserModifyRequestMother.random();
        User userWithSameMail = UserMother.randomWithMail(user.getMail());

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.findByMail(request.getMail())).thenReturn(Optional.of(userWithSameMail));

        MyalbatrossException expectedException = assertThrows(MyalbatrossException.class, () -> userModifier.modify(user.getId(), request));
        verify(userRepository, times(1)).findById(user.getId());
        verify(userRepository, times(1)).findByMail(request.getMail());
        verify(userRepository, never()).save(any());
        assertMyalbatrossException(MyalbatrossError.MAIL_ALREADY_EXISTS, expectedException);
    }

    static Stream<Arguments> correctParametersProvider() {
        User user = UserMother.random();
        return Stream.of(
                Arguments.of(user, user, UserModifyRequestMother.randomWithMail(user.getMail())), //When user email exists, but is the same user
                Arguments.of(user, null, UserModifyRequestMother.random()) //When user mail doesn't exist
        );
    }

    private void assertEquals(UserModifyRequest expected, User actual) {
        Assertions.assertEquals(expected.getUsername(), actual.getUsername());
        Assertions.assertEquals(expected.getMail(), actual.getMail());
        Assertions.assertEquals(expected.getBirthDate(), actual.getBirthDate());
    }
}
