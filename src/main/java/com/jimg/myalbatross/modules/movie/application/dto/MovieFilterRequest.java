package com.jimg.myalbatross.modules.movie.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieFilterRequest {
    private String director;
    private Long releaseYear;
}
