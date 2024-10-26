package com.jimg.myalbatross.movie.application.service;

import com.jimg.myalbatross.modules.movie.application.dto.MovieSetRequest;
import com.jimg.myalbatross.modules.movie.application.service.MovieModifier;
import com.jimg.myalbatross.modules.movie.domain.entity.Movie;
import com.jimg.myalbatross.modules.movie.domain.repository.MovieRepository;
import com.jimg.myalbatross.movie.application.dto.MovieSetRequestMother;
import com.jimg.myalbatross.movie.domain.MovieMother;
import com.jimg.myalbatross.shared.application.UnitTestCase;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossError;
import com.jimg.myalbatross.shared.domain.exception.MyalbatrossException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MovieModifierTest extends UnitTestCase {
    @Mock
    private MovieRepository movieRepository;

    private MovieModifier movieModifier;

    @BeforeEach
    public void setUp() {
        movieModifier = new MovieModifier(movieRepository);
    }

    @Test
    public void shouldModifyMovie() {
        Movie movie = MovieMother.random();
        MovieSetRequest movieSetRequest = MovieSetRequestMother.random();
        ArgumentCaptor<Movie> movieCaptor = ArgumentCaptor.forClass(Movie.class);

        when(movieRepository.findById(movie.getId())).thenReturn(Optional.of(movie));

        assertDoesNotThrow(() -> movieModifier.modifyMovie(movie.getId(), movieSetRequest));

        verify(movieRepository, times(1)).findById(movie.getId());
        verify(movieRepository, times(1)).save(movieCaptor.capture());
        assertEquals(movieSetRequest, movieCaptor.getValue());
    }

    @Test
    public void shoudThrowMyalbatrossExceptionWhenMovieNotExists() {
        Movie movie = MovieMother.random();
        MovieSetRequest movieSetRequest = MovieSetRequestMother.random();

        when(movieRepository.findById(movie.getId())).thenReturn(Optional.empty());

        MyalbatrossException exception = Assertions.assertThrows(MyalbatrossException.class, () -> movieModifier.modifyMovie(movie.getId(), movieSetRequest));
        verify(movieRepository, times(1)).findById(movie.getId());
        verify(movieRepository, never()).save(any());
        assertMyalbatrossException(MyalbatrossError.MOVIE_NOT_EXISTS, exception);
    }

    @ParameterizedTest
    @MethodSource("incorrectParametersProvider")
    public void shouldThrowMyalbatrossExceptionWhenTitleIsNullOrEmpty(MovieSetRequest incorrectParameter, MyalbatrossError error) {
        Movie movie = MovieMother.random();

        when(movieRepository.findById(movie.getId())).thenReturn(Optional.of(movie));
        MyalbatrossException exception = assertThrows(MyalbatrossException.class, () -> movieModifier.modifyMovie(movie.getId(), incorrectParameter));
        verify(movieRepository, times(1)).findById(movie.getId());
        verify(movieRepository, never()).save(any());
        assertMyalbatrossException(error, exception);
    }

    static Stream<Arguments> incorrectParametersProvider() {
        return Stream.of(
                Arguments.of(MovieSetRequestMother.randomWithEmptyTitle(), MyalbatrossError.MOVIE_TITLE_EMPTY),
                Arguments.of(MovieSetRequestMother.randomWithNullTitle(), MyalbatrossError.MOVIE_TITLE_EMPTY)
        );
    }

    private void assertEquals(MovieSetRequest expected, Movie actual) {
        Assertions.assertEquals(expected.getDirector(), actual.getDirector());
        Assertions.assertEquals(expected.getReleaseYear(), actual.getReleaseYear());
        Assertions.assertEquals(expected.getTitle(), actual.getTitle());
        Assertions.assertEquals(expected.getDuration(), actual.getDuration());
    }
}
