package com.jimg.myalbatross.modules.user.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class UserResponse {
    private UUID id;
    private String mail;
    private String username;
    private LocalDateTime birthDate;
}
