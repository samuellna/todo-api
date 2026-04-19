package br.com.samuellna.todo_api.dto.task;
import br.com.samuellna.todo_api.utils.StatusTask;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateTaskDto {
    private String title;
    private String description;
    private StatusTask status;
}
