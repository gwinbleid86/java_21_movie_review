package kg.attractor.movie_review_21.service.impl;

import kg.attractor.movie_review_21.dao.CastDao;
import kg.attractor.movie_review_21.dto.CastDto;
import kg.attractor.movie_review_21.service.CastService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CastServiceImpl implements CastService {
    private final CastDao castDao;

    @Override
    public List<CastDto> convertToDto(long movieId) {
        var castList = castDao.getCastListByMovieId(movieId);
        return castList.stream()
                .map(e -> CastDto.builder()
                        .fullName(e.getFullName())
                        .role(e.getRole())
                        .build())
                .toList();
    }
}
