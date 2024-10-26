package com.jimg.myalbatross.modules.movie.infraestructure.controller;

import com.jimg.myalbatross.modules.movie.application.dto.MovieFilterRequest;
import com.jimg.myalbatross.modules.movie.application.dto.MovieResponse;
import com.jimg.myalbatross.modules.movie.application.service.MovieFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/movie")
public class MovieGetFindController {
    private final MovieFinder movieFinder;

    @GetMapping
    public Page<MovieResponse> find(@PageableDefault Pageable pageable, MovieFilterRequest movieFilterRequest) {
        return movieFinder.findByFilters(pageable, movieFilterRequest);
    }
}
