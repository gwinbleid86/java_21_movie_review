package kg.attractor.movie_review_21.service.impl;

import kg.attractor.movie_review_21.dao.MovieDao;
import kg.attractor.movie_review_21.dto.MovieDto;
import kg.attractor.movie_review_21.dto.MovieRequest;
import kg.attractor.movie_review_21.errors.CanNotFindMovieException;
import kg.attractor.movie_review_21.model.Movie;
import kg.attractor.movie_review_21.repository.MovieRepository;
import kg.attractor.movie_review_21.service.CastService;
import kg.attractor.movie_review_21.service.DirectorService;
import kg.attractor.movie_review_21.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final DirectorService directorService;
    private final CastService castService;
    private final MovieDao movieDao;
    private final MovieRepository movieRepository;

    @Override
    public Page<MovieDto> getMovies(Pageable pageable) {
        Page<Movie> movies = movieRepository.findAll(pageable);
        var list = movieRepository.findAll(pageable).get()
                .map(this::convertToDto)
                .toList();
        return new PageImpl<>(list, pageable, movies.getTotalElements());
    }

    @Override
    public MovieDto getMovie(long id) throws RuntimeException {
        Movie movie = movieDao.findById(id)
                .orElseThrow(() -> new CanNotFindMovieException("Can not find Movie with ID: " + id));
        return convertToDto(movie);
    }

    @Override
    public void create(MovieRequest movieDto) {
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

    @Override
    public List<MovieDto> getMovies() {
        var movies = movieRepository.findAll();
        return movies.stream()
                .map(this::convertToDto)
                .toList();
    }

    private MovieDto convertToDto(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .name(movie.getName())
                .year(movie.getReleaseYear())
                .description(movie.getDescription())
//                .director(directorService.convertToDto(movie.getDirectorId()))
                .cast(castService.convertToDto(movie.getId()))
                .build();
    }

}
