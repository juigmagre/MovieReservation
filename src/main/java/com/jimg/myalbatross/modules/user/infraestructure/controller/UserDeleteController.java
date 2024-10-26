package com.jimg.myalbatross.modules.user.infraestructure.controller;

import com.jimg.myalbatross.modules.user.application.service.UserDeleter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserDeleteController {
    private final UserDeleter userDeleter;

    @DeleteMapping
    public void deleteUser(@RequestParam UUID id) {
        userDeleter.delete(id);
    }
}
