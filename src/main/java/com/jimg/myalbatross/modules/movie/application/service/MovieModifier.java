package com.jimg.myalbatross.modules.movie.application.service;

import com.jimg.myalbatross.modules.movie.application.dto.MovieSetRequest;
import com.jimg.myalbatross.modules.movie.domain.entity.Movie;
import com.jimg.myalbatross.modules.movie.domain.repository.MovieRepository;
import com.jimg.myalbatross.modules.movie.domain.vo.MovieTitle;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieModifier {
    public final MovieRepository movieRepository;

    public void modifyMovie(UUID id, MovieSetRequest request) {
        log.info("Modifying movie {} with title: {}, director: {}, release year: {}, duration: {}",
                id, request.getTitle(), request.getDirector(), request.getReleaseYear(), request.getDuration());
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new MyalbatrossException(MyalbatrossError.MOVIE_NOT_EXISTS));
        MovieTitle title = new MovieTitle(request.getTitle());
        movie.setTitle(title);
        movie.setDirector(request.getDirector());
        movie.setDuration(request.getDuration());
        movie.setReleaseYear(request.getReleaseYear());

        movieRepository.save(movie);
    }
}
