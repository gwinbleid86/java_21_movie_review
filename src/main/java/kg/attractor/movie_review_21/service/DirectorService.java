package kg.attractor.movie_review_21.service;

import kg.attractor.movie_review_21.dto.DirectorDto;

import java.util.List;

public interface DirectorService {
    DirectorDto convertToDto(Long director);

    List<DirectorDto> getList();

    DirectorDto getByMovieId(long movieId);

    void create(DirectorDto directorDto);
}
