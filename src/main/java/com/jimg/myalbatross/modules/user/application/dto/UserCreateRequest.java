package com.jimg.myalbatross.modules.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UserCreateRequest {
    private UUID id;
    private String mail;
    private String username;
    private LocalDateTime birthDate;
}
