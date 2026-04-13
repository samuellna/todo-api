package br.com.samuellna.todo_api.database.repository;

import br.com.samuellna.todo_api.database.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<UserEntity> findAll() {
        String sqlQuery = "SELECT id, name, email FROM users";
        return jdbcTemplate.query(sqlQuery, (rs, rowNum) ->
            new UserEntity(
                rs.getInt("id"), rs.getString("name"), rs.getString("email")
            )
        );
    }

    public Long create(UserEntity user) {
        String sqlQuery = "INSERT INTO users (name, email) VALUES (?, ?) RETURNING id";
        return jdbcTemplate.queryForObject(sqlQuery, Long.class, user.getName(), user.getEmail());
    }

    public List<UserEntity> findById(String id) {
        String sqlQuery = "SELECT id, name, email FROM users WHERE id = ?";
        return jdbcTemplate.query(sqlQuery, (rs, rowNum) ->
                new UserEntity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                ),id);
    }
}