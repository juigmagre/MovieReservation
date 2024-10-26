package com.jimg.myalbatross.modules.movie.infraestructure.controller;

import com.jimg.myalbatross.modules.movie.application.dto.MovieSetRequest;
import com.jimg.myalbatross.modules.movie.application.service.MovieModifier;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/movie")
public class MoviePutSetController {
    private final MovieModifier movieModifier;

    @PutMapping
    public void modify(@RequestParam UUID id, @RequestBody MovieSetRequest request) {
        movieModifier.modifyMovie(id, request);
    }
}
