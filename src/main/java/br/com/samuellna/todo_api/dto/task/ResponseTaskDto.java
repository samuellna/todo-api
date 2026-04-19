package br.com.samuellna.todo_api.dto.task;

import br.com.samuellna.todo_api.database.model.UserEntity;
import br.com.samuellna.todo_api.utils.StatusTask;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseTaskDto {
    private Long id;
    private String title;
    private String description;
    private StatusTask status;
    private Timestamp createdAt;
    private UserEntity user;
}
