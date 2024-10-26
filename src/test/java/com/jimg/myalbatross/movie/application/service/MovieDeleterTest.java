package com.jimg.myalbatross.movie.application.service;

import com.jimg.myalbatross.modules.movie.application.service.MovieRemover;
import com.jimg.myalbatross.modules.movie.domain.entity.Movie;
import com.jimg.myalbatross.modules.movie.domain.repository.MovieRepository;
import com.jimg.myalbatross.movie.domain.MovieMother;
import com.jimg.myalbatross.shared.application.UnitTestCase;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovieDeleterTest extends UnitTestCase {
    @Mock
    private MovieRepository movieRepository;

    private MovieRemover movieRemover;

    @BeforeEach
    public void setUp() {
        movieRemover = new MovieRemover(movieRepository);
    }

    @Test
    public void shouldDeleteMovie() {
        Movie movie = MovieMother.random();

        when(movieRepository.findById(movie.getId())).thenReturn(Optional.of(movie));

        assertDoesNotThrow(() -> movieRemover.remove(movie.getId()));
        verify(movieRepository, times(1)).delete(movie);
    }

    @Test
    public void shouldThrowExceptionWhenMovieNotFound() {
        Movie movie = MovieMother.random();

        when(movieRepository.findById(movie.getId())).thenReturn(Optional.empty());

        MyalbatrossException actualException = assertThrows(MyalbatrossException.class, () -> movieRemover.remove(movie.getId()));
        verify(movieRepository, never()).delete(any(Movie.class));
        assertMyalbatrossException(MyalbatrossError.MOVIE_NOT_EXISTS, actualException);
    }
}
