package com.jimg.myalbatross.modules.movie.infraestructure.controller;

import com.jimg.myalbatross.modules.movie.application.service.MovieRemover;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/movie")
public class MovieDeleteController {
    private final MovieRemover movieRemover;

    @DeleteMapping
    public void remove(@RequestParam UUID movieId) {
        movieRemover.remove(movieId);
    }
}
