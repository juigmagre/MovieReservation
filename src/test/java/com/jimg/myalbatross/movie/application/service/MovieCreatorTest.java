package com.jimg.myalbatross.movie.application.service;

import com.jimg.myalbatross.modules.movie.application.dto.MovieCreateRequest;
import com.jimg.myalbatross.modules.movie.application.service.MovieCreator;
import com.jimg.myalbatross.modules.movie.domain.entity.Movie;
import com.jimg.myalbatross.modules.movie.domain.repository.MovieRepository;
import com.jimg.myalbatross.movie.application.dto.MovieCreateRequestMother;
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

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MovieCreatorTest extends UnitTestCase {
    @Mock
    private MovieRepository movieRepository;

    private MovieCreator movieCreator;

    @BeforeEach
    void setUp() {
        movieCreator = new MovieCreator(movieRepository);
    }

    @Test
    void shouldCreateUser() {
        ArgumentCaptor<Movie> movieCaptor = ArgumentCaptor.forClass(Movie.class);
        MovieCreateRequest movieCreateRequest = MovieCreateRequestMother.random();

        when(movieRepository.existsById(movieCreateRequest.getId())).thenReturn(false);

        assertDoesNotThrow(() -> movieCreator.create(movieCreateRequest));

        verify(movieRepository, times(1)).existsById(movieCreateRequest.getId());
        verify(movieRepository, times(1)).save(movieCaptor.capture());
        assertEquals(movieCreateRequest, movieCaptor.getValue());
    }

    @Test
    void shouldThrowMyalbatrossExceptionWhenMovieExists() {
        MovieCreateRequest movieCreateRequest = MovieCreateRequestMother.random();

        when(movieRepository.existsById(movieCreateRequest.getId())).thenReturn(true);

        MyalbatrossException actualException = assertThrows(MyalbatrossException.class, () -> movieCreator.create(movieCreateRequest));

        verify(movieRepository, times(1)).existsById(movieCreateRequest.getId());
        verify(movieRepository, never()).save(any());
        assertMyalbatrossException(MyalbatrossError.MOVIE_ALREADY_EXISTS, actualException);
    }

    @ParameterizedTest
    @MethodSource("incorrectParametersProvider")
    void shouldThrowMyalbatrossExceptionWhenParameterIsNotCorrect(MovieCreateRequest invalidRequest, MyalbatrossError expectedError) {
        when(movieRepository.existsById(invalidRequest.getId())).thenReturn(false);

        MyalbatrossException actualException = assertThrows(MyalbatrossException.class, () -> movieCreator.create(invalidRequest));
        verify(movieRepository, never()).save(any());
        assertMyalbatrossException(expectedError, actualException);
    }

    static Stream<Arguments> incorrectParametersProvider() {
        return Stream.of(
                Arguments.of(MovieCreateRequestMother.randomWithNullTitle(), MyalbatrossError.MOVIE_TITLE_EMPTY),
                Arguments.of(MovieCreateRequestMother.randomWithEmptyTitle(), MyalbatrossError.MOVIE_TITLE_EMPTY)
        );
    }

    private void assertEquals(MovieCreateRequest expected, Movie actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getTitle(), actual.getTitle());
        Assertions.assertEquals(expected.getDirector(), actual.getDirector());
        Assertions.assertEquals(expected.getReleaseYear(), actual.getReleaseYear());
        Assertions.assertEquals(expected.getDuration(), actual.getDuration());
    }
}
