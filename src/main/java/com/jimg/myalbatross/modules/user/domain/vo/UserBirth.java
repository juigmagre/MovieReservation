package com.jimg.myalbatross.modules.user.domain.vo;

import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.Period;

@RequiredArgsConstructor
public class UserBirth {
    private LocalDateTime birthDate;

    public UserBirth(LocalDateTime birthDate) {
        Period period = Period.between(birthDate.toLocalDate(), LocalDateTime.now().toLocalDate());

        if (period.getYears() < 18) {
            throw new MyalbatrossException(MyalbatrossError.USER_AGE_NOT_CORRECT);
        }
        this.birthDate = birthDate;
    }

    public LocalDateTime value() {
        return this.birthDate;
    }
}
