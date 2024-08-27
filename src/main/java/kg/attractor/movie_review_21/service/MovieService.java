package kg.attractor.movie_review_21.service;

import kg.attractor.movie_review_21.dto.MovieDto;
import kg.attractor.movie_review_21.dto.MovieRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {
    List<MovieDto> getMovies();

    MovieDto getMovie(long id) throws RuntimeException;

    void create(MovieRequest movieDto);

    Page<MovieDto> getMovies(Pageable pageable);
}
