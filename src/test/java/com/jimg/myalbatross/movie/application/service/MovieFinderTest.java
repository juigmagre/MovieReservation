package com.jimg.myalbatross.movie.application.service;

import com.jimg.myalbatross.modules.movie.application.criteria.MovieSpecificationBuilder;
import com.jimg.myalbatross.modules.movie.application.mapper.MovieMapper;
import com.jimg.myalbatross.modules.movie.application.service.MovieFinder;
import com.jimg.myalbatross.modules.movie.domain.repository.MovieRepository;
import com.jimg.myalbatross.shared.application.UnitTestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class MovieFinderTest extends UnitTestCase {
    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieMapper movieMapper;

    @Mock
    private MovieSpecificationBuilder specificationBuilder;

    private MovieFinder movieFinder;

    @BeforeEach
    public void setUp() {
        movieFinder = new MovieFinder(movieRepository, movieMapper);
    }

    @Test
    public void shouldFindMovie() {

    }
}
