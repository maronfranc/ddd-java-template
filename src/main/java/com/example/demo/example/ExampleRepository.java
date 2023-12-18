package com.example.demo.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ServerErrorException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class ExampleRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    static class ExampleRowMapper implements RowMapper<Example> {
        @Override
        public Example mapRow(ResultSet rs, int rowNum) throws SQLException {
            Example example = new Example();
            example.setId(rs.getString("id"));
            example.setTitle(rs.getString("title"));
            return example;
        }
    }

    public List<Example> findAll() {
        String query = "SELECT id, title FROM examples";
        return jdbcTemplate.query(query, new ExampleRepository.ExampleRowMapper());
    }

    public Optional<Example> findById(String id) {
        String query = "SELECT id, title FROM users WHERE id = ?";
        Example result = jdbcTemplate.queryForObject(query,
                new BeanPropertyRowMapper<>(Example.class), id);
        return Optional.ofNullable(result);
    }

    public boolean existsByEmail(String email) {
        String query = "SELECT id FROM examples WHERE email = ?";
        Example result = jdbcTemplate.queryForObject(query,
                new BeanPropertyRowMapper<>(Example.class), email);
        return result == null;
    }

    public int deleteById(String id) {
        String query = "DELETE FROM examples WHERE id = ?";
        return jdbcTemplate.update(query, id);
    }

    public String insertReturningId(String title, String description) {
        final String SQL = "INSERT INTO examples (title, description) VALUES(?, ?) RETURNING id";
        KeyHolder keyHolder = new GeneratedKeyHolder();
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

    public int update(String id, String title, String description) {
        String query = "UPDATE examples SET title = ? WHERE id = ?";
        return jdbcTemplate.update(query, title, description);
    }
}

//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
//public interface ExampleRepository {
//    @Select("SELECT * FROM student WHERE id = #{id}")
//    @Delete("DELETE FROM student WHERE id = #{id}")
//    @Insert("INSERT INTO student(id, name, passport) VALUES (#{id}, #{name}, #{passport})")
//    @Update("Update student set name=#{name}, passport=#{passport} where id=#{id}")
//    List<Example> findAll();
//    Optional<Example> findById(String id);
//    boolean existsByEmail(String email);
//    int deleteById(String id);
//    int insert(String title, String description);
//    int update(String id, String title, String description);
//}

