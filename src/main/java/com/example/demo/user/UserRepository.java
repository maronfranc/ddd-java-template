package com.example.demo.user;

//import org.springframework.data.jpa.repository.JpaRepository;
//public interface UserRepository extends JpaRepository<User, Long> { }

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.web.client.HttpServerErrorException;

import javax.swing.text.html.Option;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Getter
    final public String tableName = "users";

    static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setSalt(rs.getString("salt"));
            return user;
        }
    }

    public List<User> findAll() {
        String query = "SELECT id, email FROM users";
        return jdbcTemplate.query(query, new UserRowMapper());
    }

    public Optional<User> findById(String id) {
        String query = "SELECT id, email FROM users WHERE id = ?";
        User result = jdbcTemplate.queryForObject(query,
                new BeanPropertyRowMapper<>(User.class), id);
        return Optional.ofNullable(result);
    }

    public boolean existsByEmail(String email) {
        String query = "SELECT id FROM " + this.tableName + " WHERE email = ?";
        User result = jdbcTemplate.queryForObject(query,
                new BeanPropertyRowMapper<>(User.class), email);
        return result == null;
    }

    public void deleteById(String id) {
        String query = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    public String insertReturningId(String email, String password) {
        final String SQL= "INSERT INTO users (id, email, password) " + "VALUES(?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

//        return jdbcTemplate.update(query,
//                user.getId(), user.getEmail(), user.getPassword(), user.getSalt());
        jdbcTemplate.update(conn -> {
            return conn.prepareStatement(SQL,
                    Statement.RETURN_GENERATED_KEYS);
        }, keyHolder);
        String returnedKey = keyHolder.getKeyAs(String.class);
        if (returnedKey == null) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Error returning insert key");
        }
        return returnedKey;
    }

    public int update(User user) {
        String query = "UPDATE users SET email = ?, PASSWORD = ? " + " WHERE id = ?";
        return jdbcTemplate.update(query,
                user.getEmail(),  user.getPassword(),  user.getId());
    }
}
