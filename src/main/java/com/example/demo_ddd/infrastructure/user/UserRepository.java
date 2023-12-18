package com.maron.ddd_java_sample.infrastructure.user;

//import org.springframework.data.jpa.repository.JpaRepository;
//public interface UserRepository extends JpaRepository<User, Long> { }

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.swing.text.html.Option;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

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
        String query = "select * from users";
        return jdbcTemplate.query(query, new UserRowMapper());
    }

    public Optional<User> findById(String id) {
        String query = "select * from users where id=?";
        User result = jdbcTemplate.queryForObject(query,
                new BeanPropertyRowMapper<>(User.class), id);
        return Optional.ofNullable(result);
    }

    public Optional<User> findByEmail(String email) {
        String query = "select id from users where email=?";
        User result = jdbcTemplate.queryForObject(query,
                new BeanPropertyRowMapper<>(User.class), email);
        return  Optional.ofNullable(result);
    }

    public void deleteById(String id) {
        String query = "delete from users where id=?";
        jdbcTemplate.update(query, id);
    }

    public int insert(User user) {
        String query = "insert into users (id, email, password) " + "values(?,  ?, ?)";
        return jdbcTemplate.update(query,
                user.getId(), user.getEmail(), user.getPassword());
    }

    public int update(User user) {
        String query = "update users " + " set name = ?, passport_number = ? " + " where id = ?";
        return jdbcTemplate.update(query,
                 user.getEmail(),  user.getPassword(),  user.getId());
    }
}
