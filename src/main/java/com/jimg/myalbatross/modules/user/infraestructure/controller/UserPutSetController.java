package com.jimg.myalbatross.modules.user.infraestructure.controller;

import com.jimg.myalbatross.modules.user.application.dto.UserModifyRequest;
import com.jimg.myalbatross.modules.user.application.service.UserModifier;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserPutSetController {
    private final UserModifier userModifier;

    @PutMapping
    public void modify(@RequestBody UserModifyRequest request, @RequestParam UUID id) {
        userModifier.modify(id, request);
    }
}
