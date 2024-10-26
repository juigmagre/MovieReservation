package com.jimg.myalbatross.modules.movie.infraestructure.controller;

import com.jimg.myalbatross.modules.movie.application.dto.MovieCreateRequest;
import com.jimg.myalbatross.modules.movie.application.service.MovieCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/movie")
public class MoviePostCreateController {
    private final MovieCreator movieCreator;

    @PostMapping
    public void create(@RequestBody MovieCreateRequest request) {
        movieCreator.create(request);
    }
}
