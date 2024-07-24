package kg.attractor.movie_review_21.service.impl;

import kg.attractor.movie_review_21.dao.MovieDao;
import kg.attractor.movie_review_21.dto.DirectorDto;
import kg.attractor.movie_review_21.model.Director;
import kg.attractor.movie_review_21.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final MovieDao movieDao;

    @Override
    public DirectorDto convertToDto(Director director) {
        return DirectorDto.builder()
                .fullName(director.getFullName())
                .build();
    }

    @Override
    public List<DirectorDto> getList() {
        var movies = movieDao.getMovies();

        return movies.stream()
                .map(e -> DirectorDto.builder()
                        .fullName(e.getDirector().getFullName())
                        .build())
                .distinct()
                .toList();
    }

    @Override
    public DirectorDto getByMovieId(int movieId) {
        return movieDao.getMovies().stream()
                .filter(e -> e.getId() == movieId)
                .map(e -> DirectorDto.builder().fullName(e.getDirector().getFullName()).build())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    @Override
    public void create(DirectorDto directorDto) {
        System.out.println(directorDto);
    }
}
