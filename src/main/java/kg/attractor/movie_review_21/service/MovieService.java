package kg.attractor.movie_review_21.service;

import kg.attractor.movie_review_21.dto.MovieDto;
import kg.attractor.movie_review_21.dto.MovieRequest;

import java.util.List;

public interface MovieService {
    List<MovieDto> getMovies(Integer page, Integer size);

    MovieDto getMovie(long id) throws RuntimeException;

    void create(MovieRequest movieDto);

    List<MovieDto> getMovies();
}
