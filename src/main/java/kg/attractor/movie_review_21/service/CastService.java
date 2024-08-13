package kg.attractor.movie_review_21.service;

import kg.attractor.movie_review_21.dto.CastDto;

import java.util.List;

public interface CastService {
    List<CastDto> convertToDto(long casts);

    List<CastDto> getList();
}
