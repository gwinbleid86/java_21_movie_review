package kg.attractor.movie_review_21.dao;

import kg.attractor.movie_review_21.model.Cast;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CastDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Cast> getCastListByMovieId(long movieId) {
        String sql = """
                select * from CAST_MEMBER cm
                inner join MOVIE_CAST_MEMBER mcm on cm.ID = mcm.CAST_MEMBER_ID
                inner join MOVIE m on mcm.MOVIE_ID = m.ID
                where m.id = ?;
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Cast.class), movieId);
    }
}
