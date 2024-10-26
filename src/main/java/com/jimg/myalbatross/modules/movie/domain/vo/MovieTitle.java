package com.jimg.myalbatross.modules.movie.domain.vo;

import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;

@NoArgsConstructor
public class MovieTitle {
    private String title;

    public MovieTitle(String title) {
        if (isNull(title) || title.isEmpty()) {
            throw new MyalbatrossException(MyalbatrossError.MOVIE_TITLE_EMPTY);
        }

        this.title = title;
    }

    public String value() {
        return this.title;
    }
}