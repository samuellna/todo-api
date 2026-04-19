package br.com.samuellna.todo_api.database.repository;

import br.com.samuellna.todo_api.database.model.TaskEntity;
import br.com.samuellna.todo_api.dto.task.TaskDto;
import br.com.samuellna.todo_api.dto.task.UpdateTaskDto;
import br.com.samuellna.todo_api.utils.StatusTask;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TaskRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<TaskEntity> findAll() {
        String sqlQuery = "SELECT id, title, description, status, created_at, user_id FROM tasks";
        return jdbcTemplate.query(sqlQuery, (rs, rowNum) ->
            new TaskEntity(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("description"),
                StatusTask.valueOf(rs.getString("status")),
                rs.getTimestamp("created_at"),
                rs.getLong("user_id")
            )
        );
    }

    public Long create(TaskDto task) {
        String sqlQuery =
                "INSERT INTO tasks (title, description, status, created_at, user_id) VALUES (?, ?, ?, ?, ?) RETURNING id";
        return jdbcTemplate.queryForObject(
            sqlQuery,
            Long.class,
            task.getTitle(),
            task.getDescription(),
            StatusTask.PENDING.toString(),
            Timestamp.valueOf(LocalDateTime.now()),
            task.getUserId()
        );
    }

    public Optional<TaskEntity> findById(Long id) {
        String sqlQuery = "SELECT id, title, description, status, created_at, user_id FROM tasks WHERE id = ?";
        List<TaskEntity> task = jdbcTemplate.query(sqlQuery,
            (rs, rowNum) -> new TaskEntity(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("description"),
                StatusTask.valueOf(rs.getString("status")),
                rs.getTimestamp("created_at"),
                rs.getLong("user_id")
            ),
        id);
        return task.stream().findFirst();
    }

    public List<TaskEntity> findByUserId(Long userId) {
        String sqlQuery = "SELECT id, title, description, status, created_at, user_id FROM tasks WHERE user_id = ?";
        return jdbcTemplate.query(sqlQuery,
            (rs, rowNum) -> new TaskEntity(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("description"),
                StatusTask.valueOf(rs.getString("status")),
                rs.getTimestamp("created_at"),
                rs.getLong("user_id")
            ),
        userId).stream().toList();
    }

    public void update(Long id, UpdateTaskDto taskDto) {
        List<Object> params = new ArrayList<>();
        StringBuilder sqlQuery = new StringBuilder("UPDATE tasks SET ");

        if(taskDto.getTitle() != null) {
            sqlQuery.append("title = ?");
            params.add(taskDto.getTitle());
        }

        if(taskDto.getDescription() != null) {
            sqlQuery.append(", description = ?");
            params.add(taskDto.getDescription());
        }

        if(taskDto.getStatus() != null) {
            sqlQuery.append(", status = ?");
            params.add(taskDto.getStatus().toString());
        }

        if(params.isEmpty()) return;

        sqlQuery.append(" WHERE id = ?");

        if(params.size() == 1 &&
            (params.get(0) == taskDto.getDescription() || params.get(0) == taskDto.getStatus().toString()))
            sqlQuery.delete(17, 18); // removes the comma

        params.add(id);

        jdbcTemplate.update(
            sqlQuery.toString(),
            params.toArray()
        );
    }

    public void delete(Long id) {
        String sqlQuery = "DELETE FROM tasks WHERE id = ?";
        jdbcTemplate.update(sqlQuery, id);
    }
}
