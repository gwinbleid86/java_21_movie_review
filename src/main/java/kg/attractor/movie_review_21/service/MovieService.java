package kg.attractor.movie_review_21.service;

import kg.attractor.movie_review_21.dto.MovieDto;

import java.util.List;

public interface MovieService {
    List<MovieDto> getMovies();

    MovieDto getMovie(int id) throws RuntimeException;

    void create(MovieDto movieDto);
}
