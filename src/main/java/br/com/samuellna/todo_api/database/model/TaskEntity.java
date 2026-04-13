package br.com.samuellna.todo_api.database.model;

import br.com.samuellna.todo_api.utils.StatusTask;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class TaskEntity {
    private int id;
    private String title;
    private String description;
    private StatusTask status;
    private Date createdAt;
}
