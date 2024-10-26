package com.jimg.myalbatross.modules.movie.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class MovieCreateRequest {
    private UUID id;
    private String title;
    private String director;
    private Long releaseYear;
    private Double duration;
}
