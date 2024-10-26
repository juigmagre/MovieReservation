package com.jimg.myalbatross.modules.movie.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MovieResponse {
    private UUID id;
    private String title;
    private String director;
    private Long releaseYear;
    private Double duration;
}
