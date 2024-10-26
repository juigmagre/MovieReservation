package com.jimg.myalbatross.modules.user.infraestructure.controller;

import com.jimg.myalbatross.modules.user.application.dto.UserCreateRequest;
import com.jimg.myalbatross.modules.user.application.service.UserCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserPostCreateController {
    private final UserCreator userCreator;

    @PostMapping
    public void create(@RequestBody UserCreateRequest userCreateRequest) {
        userCreator.create(userCreateRequest);
    }
}
