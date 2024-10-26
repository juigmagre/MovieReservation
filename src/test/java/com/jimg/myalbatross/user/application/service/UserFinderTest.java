package com.jimg.myalbatross.user.application.service;

import com.jimg.myalbatross.modules.user.application.dto.UserResponse;
import com.jimg.myalbatross.modules.user.application.mapper.UserMapper;
import com.jimg.myalbatross.modules.user.application.service.UserFinder;
import com.jimg.myalbatross.modules.user.domain.entity.User;
import com.jimg.myalbatross.modules.user.domain.repository.UserRepository;
import com.jimg.myalbatross.shared.application.UnitTestCase;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import com.jimg.myalbatross.user.application.dto.UserResponseMother;
import com.jimg.myalbatross.user.domain.entity.UserMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class UserFinderTest extends UnitTestCase {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    private UserFinder userFinder;

    @BeforeEach
    public void setUp() {
        userFinder = new UserFinder(userRepository, userMapper);
    }

    @Test
    public void shouldFindUserById() {
        User user = UserMother.random();
        UserResponse userResponse = UserResponseMother.withUser(user);
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userMapper.toUserResponse(user)).thenReturn(userResponse);

        assertDoesNotThrow(() -> userFinder.findById(user.getId()));

        verify(userRepository, times(1)).findById(user.getId());
        verify(userMapper, times(1)).toUserResponse(userCaptor.capture());
        assertEquals(userResponse, userCaptor.getValue());
    }

    @Test
    public void shouldThrowMyalbatrossExceptionWhenUserNotExists() {
        User user = UserMother.random();

        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        MyalbatrossException expectedException = Assertions.assertThrows(MyalbatrossException.class, () -> userFinder.findById(user.getId()));

        verify(userRepository, times(1)).findById(user.getId());
        verify(userMapper, never()).toUserResponse(any());
        assertMyalbatrossException(MyalbatrossError.USER_NOT_EXISTS, expectedException);
    }

    private void assertEquals(UserResponse expected, User actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getBirthDate(), actual.getBirthDate());
        Assertions.assertEquals(expected.getMail(), actual.getMail());
        Assertions.assertEquals(expected.getUsername(), actual.getUsername());
    }
}
