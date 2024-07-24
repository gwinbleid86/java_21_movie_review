package kg.attractor.movie_review_21.service;

import kg.attractor.movie_review_21.dto.DirectorDto;
import kg.attractor.movie_review_21.model.Director;

import java.util.List;

public interface DirectorService {
    DirectorDto convertToDto(Director director);

    List<DirectorDto> getList();

    DirectorDto getByMovieId(int movieId);

    void create(DirectorDto directorDto);
}
