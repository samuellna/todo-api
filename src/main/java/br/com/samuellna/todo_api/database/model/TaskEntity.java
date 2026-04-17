package br.com.samuellna.todo_api.database.model;

import br.com.samuellna.todo_api.utils.StatusTask;
import lombok.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class TaskEntity {
    private Long id;
    private String title;
    private String description;
    private StatusTask status;
    private Timestamp createdAt;
}
