package com.jimg.myalbatross.modules.movie.application.service;

import com.jimg.myalbatross.modules.movie.application.dto.MovieCreateRequest;
import com.jimg.myalbatross.modules.movie.domain.entity.Movie;
import com.jimg.myalbatross.modules.movie.domain.repository.MovieRepository;
import com.jimg.myalbatross.modules.movie.domain.vo.MovieTitle;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieCreator {
    private final MovieRepository movieRepository;

    public void create(MovieCreateRequest request) {
        log.info("Creating movie with id: {}, title: {}, director: {}, releaseYear: {} and duration: {}",
                request.getId(), request.getTitle(), request.getDirector(), request.getReleaseYear(), request.getDuration());
        if (movieRepository.existsById(request.getId())) {
            throw new MyalbatrossException(MyalbatrossError.MOVIE_ALREADY_EXISTS);
        }

        MovieTitle title = new MovieTitle(request.getTitle());

        Movie movie = new Movie(request.getId(), title, request.getDirector(), request.getReleaseYear(), request.getDuration());
        movieRepository.save(movie);
    }
}
