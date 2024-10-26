package com.jimg.myalbatross.modules.user.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UserModifyRequest {
    private String mail;
    private String username;
    private LocalDateTime birthDate;
}
