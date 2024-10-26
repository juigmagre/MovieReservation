package com.jimg.myalbatross.modules.movie.application.service;

import com.jimg.myalbatross.modules.movie.application.criteria.MovieSpecificationBuilder;
import com.jimg.myalbatross.modules.movie.application.dto.MovieFilterRequest;
import com.jimg.myalbatross.modules.movie.application.dto.MovieResponse;
import com.jimg.myalbatross.modules.movie.application.mapper.MovieMapper;
import com.jimg.myalbatross.modules.movie.domain.entity.Movie;
import com.jimg.myalbatross.modules.movie.domain.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieFinder {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;


    public Page<MovieResponse> findByFilters(Pageable pageable, MovieFilterRequest movieFilterRequest) {
        log.info("Finding movies with director: {}, release year: {}, page: {} and size: {}",
                movieFilterRequest.getDirector(), movieFilterRequest.getReleaseYear(),
                pageable.getPageNumber(), pageable.getPageSize());
        Specification<Movie> specification = MovieSpecificationBuilder.createSpecification(movieFilterRequest);
        Page<Movie> movies = movieRepository.findAll(specification, pageable);
        List<MovieResponse> moviesResponse = movieMapper.toMovieResponseList(movies.getContent());
        return new PageImpl<>(moviesResponse, pageable, moviesResponse.size());
    }
}
