package br.com.samuellna.todo_api.dto.user;

import br.com.samuellna.todo_api.database.model.TaskEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseUserDto {
    private Long id;
    private String name;
    private String email;
    private List<TaskEntity> tasks;
}
