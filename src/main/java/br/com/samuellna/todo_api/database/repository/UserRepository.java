package br.com.samuellna.todo_api.database.repository;

import br.com.samuellna.todo_api.database.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<UserEntity> findAll() {
        String sqlQuery = "SELECT id, name, email FROM users";
        return jdbcTemplate.query(sqlQuery, (rs, rowNum) ->
            new UserEntity(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email")
            )
        );
    }

    public Long create(UserEntity user) {
        String sqlQuery = "INSERT INTO users (name, email) VALUES (?, ?) RETURNING id";
        return jdbcTemplate.queryForObject(
            sqlQuery,
            Long.class,
            user.getName(),
            user.getEmail()
        );
    }

    public Optional<UserEntity> findById(Long id) {
        String sqlQuery = "SELECT id, name, email FROM users WHERE id = ?";
        List<UserEntity> user = jdbcTemplate.query(sqlQuery, (rs, rowNum) ->
            new UserEntity(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email")
            ),
        id);
        return user.stream().findFirst();
    }
}