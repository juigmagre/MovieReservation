package com.jimg.myalbatross.modules.movie.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MovieSetRequest {
    private String title;
    private String director;
    private Long releaseYear;
    private Double duration;
}
