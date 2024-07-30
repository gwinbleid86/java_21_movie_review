package kg.attractor.movie_review_21.dao;

import kg.attractor.movie_review_21.model.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ImageDao {
    private final JdbcTemplate jdbcTemplate;

    public void save(Image image) {
        String sql = """
                 insert into IMAGES(filename, movie_id)
                 values ( ?, ? );
                """;
        jdbcTemplate.update(sql, image.getFilename(), image.getMovieId());
    }

    public Optional<Image> getImage(Long id) {
        String sql = "select * from IMAGES where id=?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Image.class), id)
                )
        );
    }

}
