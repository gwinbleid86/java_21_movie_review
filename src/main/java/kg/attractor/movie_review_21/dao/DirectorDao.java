package kg.attractor.movie_review_21.dao;

import kg.attractor.movie_review_21.model.Director;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DirectorDao {
    private final JdbcTemplate jdbcTemplate;

    public Optional<Director> findById(long id) {
        String sql = "SELECT * FROM director WHERE id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Director.class), id)
                )
        );
    }

    public List<Director> findAll() {
        String sql = "select * from director";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Director.class));
    }

    public Optional<Director> findByMovieId(long movieId) {
        String sql = """
                select * from director d, movie m
                where d.id = m.director_id
                and m.id = ?;
                """;
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Director.class), movieId)
                )
        );
    }
}
