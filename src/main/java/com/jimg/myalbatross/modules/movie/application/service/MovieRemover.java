package com.jimg.myalbatross.modules.movie.application.service;

import com.jimg.myalbatross.modules.movie.domain.entity.Movie;
import com.jimg.myalbatross.modules.movie.domain.repository.MovieRepository;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieRemover {
    private final MovieRepository movieRepository;

    public void remove(UUID id) {
        log.info("Removing movie with id {}", id);
        Movie movieToRemove = movieRepository.findById(id).orElseThrow(() -> new MyalbatrossException(MyalbatrossError.MOVIE_NOT_EXISTS));

        movieRepository.delete(movieToRemove);
    }
}
