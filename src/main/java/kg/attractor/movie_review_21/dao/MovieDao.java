package kg.attractor.movie_review_21.dao;

import kg.attractor.movie_review_21.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MovieDao {
    private final JdbcTemplate jdbcTemplate;

    public List<Movie> getMovies() {
        String sql = "select * from movie";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Movie.class));
    }

    public List<Movie> getMovies(Integer limit, Integer offset) {
        String sql = """
                select * from movie
                limit ?
                offset ?;
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Movie.class), limit, offset);
    }

    public void addMovie(Movie movie) {

    }

    public Optional<Movie> findById(long id) {
        String sql = "select * from movie where id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Movie.class), id)
                )
        );
    }
}
