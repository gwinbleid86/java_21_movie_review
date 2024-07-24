package kg.attractor.movie_review_21.dao;

import kg.attractor.movie_review_21.dao.mappers.UserMapper;
import kg.attractor.movie_review_21.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final KeyHolder keyHolder = new GeneratedKeyHolder();

    public List<User> getAllUsers() {
        String sql = "select * from users";
        return jdbcTemplate.query(sql, new UserMapper());
    }

//    public User getUserById(int id) {
//        String sql = "select * from users where id = ?";
//        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
//    }

    public Optional<User> getUserById(int id) {
        String sql = "select * from users where id = ?";
        return Optional.ofNullable(
                DataAccessUtils.singleResult(
                        jdbcTemplate.query(sql, new UserMapper(), id)
                )
        );
    }

    public Optional<User> getUserByUsername(String username) {
        String sql = "select * from users where name = ?";
        User user = jdbcTemplate.queryForObject(sql, new UserMapper(), username);
        return Optional.ofNullable(user);
    }

    public void create(User user) {
        String sql = "insert into users (name, password) values (:name, :password)";
        namedParameterJdbcTemplate.update(
                sql,
                new MapSqlParameterSource()
                        .addValue("name", user.getUsername())
                        .addValue("password", user.getPassword())
        );
    }

    public Integer create(String username, String password) {
        String sql = "insert into users (name, password) values (?, ?)";

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, username);
            ps.setString(2, password);
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }


}
