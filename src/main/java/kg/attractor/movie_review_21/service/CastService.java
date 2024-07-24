package kg.attractor.movie_review_21.service;

import kg.attractor.movie_review_21.dto.CastDto;
import kg.attractor.movie_review_21.model.Cast;

import java.util.List;

public interface CastService {
    List<CastDto> convertToDto(List<Cast> casts);
}
