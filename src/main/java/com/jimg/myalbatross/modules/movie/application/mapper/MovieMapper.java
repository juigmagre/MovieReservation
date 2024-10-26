package com.jimg.myalbatross.modules.movie.application.mapper;

import com.jimg.myalbatross.modules.movie.application.dto.MovieResponse;
import com.jimg.myalbatross.modules.movie.domain.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovieMapper {
    MovieResponse toMovieResponse(Movie movie);

    List<MovieResponse> toMovieResponseList(List<Movie> movies);
}
