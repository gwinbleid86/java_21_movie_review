package kg.attractor.movie_review_21.service.impl;

import kg.attractor.movie_review_21.dao.MovieDao;
import kg.attractor.movie_review_21.dto.MovieDto;
import kg.attractor.movie_review_21.errors.CanNotFindMovieException;
import kg.attractor.movie_review_21.model.Movie;
import kg.attractor.movie_review_21.service.CastService;
import kg.attractor.movie_review_21.service.DirectorService;
import kg.attractor.movie_review_21.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.properties.SwaggerUiOAuthProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final DirectorService directorService;
    private final CastService castService;
    private final MovieDao movieDao;
    private final SwaggerUiOAuthProperties swaggerUiOAuthProperties;

    @Override
    public List<MovieDto> getMovies(Integer page, Integer size) {
        return movieDao.getMovies(size, size * page).stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public MovieDto getMovie(long id) throws RuntimeException {
        Movie movie = movieDao.findById(id)
                .orElseThrow(() -> new CanNotFindMovieException("Can not find Movie with ID: " + id));
        return convertToDto(movie);
    }

    @Override
    public void create(MovieDto movieDto) {
//        var casts = movieDto.getCast().stream()
//                .map(e -> Cast.builder()
//                        .fullName(e.getFullName())
//                        .role(e.getRole())
//                        .build())
//                .toList();
//        movieDao.addMovie(Movie.builder()
//                .id(movieDto.getId())
//                .name(movieDto.getName())
//                .year(movieDto.getYear())
//                .description(movieDto.getDescription())
//                .director(Director.builder()
//                        .fullName(movieDto.getDirector().getFullName())
//                        .build())
//                .cast(casts)
//                .build());
    }

    private MovieDto convertToDto(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .name(movie.getName())
                .year(movie.getYear())
                .description(movie.getDescription())
                .director(directorService.convertToDto(movie.getDirectorId()))
                .cast(castService.convertToDto(movie.getId()))
                .build();
    }

}
