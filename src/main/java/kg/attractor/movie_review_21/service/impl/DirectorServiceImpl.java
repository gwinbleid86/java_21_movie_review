package kg.attractor.movie_review_21.service.impl;

import kg.attractor.movie_review_21.dao.DirectorDao;
import kg.attractor.movie_review_21.dao.MovieDao;
import kg.attractor.movie_review_21.dto.DirectorDto;
import kg.attractor.movie_review_21.model.Director;
import kg.attractor.movie_review_21.repository.DirectorRepository;
import kg.attractor.movie_review_21.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {

    private final DirectorDao directorDao;
    private final MovieDao movieDao;
    private final DirectorRepository directorRepository;

    @Override
    public DirectorDto convertToDto(Long directorId) {
        Director director = directorDao.findById(directorId)
                .orElseThrow(() -> new NoSuchElementException("Can not find Director with ID: " + directorId));
        return DirectorDto.builder()
                .id(director.getId())
                .fullName(director.getFullname())
                .build();
    }

    @Override
    public List<DirectorDto> getList() {
        List<Director> directorList = directorRepository.findAll();
//        List<Director> directorList = directorDao.findAll();
        return directorList.stream()
                .map(e -> DirectorDto.builder()
                        .id(e.getId())
                        .fullName(e.getFullname())
                        .build())
                .toList();
    }

    @Override
    public DirectorDto getByMovieId(long movieId) {
        Director director = directorRepository.findByMovieId(movieId)

//        Director director = directorDao.findByMovieId(movieId)
                .orElseThrow(() -> new NoSuchElementException("Can not find Director with movieId: " + movieId));
        return DirectorDto.builder()
                .id(director.getId())
                .fullName(director.getFullname())
                .build();
    }

    @Override
    public void create(DirectorDto directorDto) {
        System.out.println(directorDto);
    }
}
