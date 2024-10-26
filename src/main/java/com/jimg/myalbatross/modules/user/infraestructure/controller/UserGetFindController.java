package com.jimg.myalbatross.modules.user.infraestructure.controller;

import com.jimg.myalbatross.modules.user.application.dto.UserResponse;
import com.jimg.myalbatross.modules.user.application.service.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserGetFindController {
    private final UserFinder userFinder;

    @GetMapping
    public UserResponse findById(@RequestParam UUID id) {
        return userFinder.findById(id);
    }
}
