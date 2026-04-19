package br.com.samuellna.todo_api.dto.task;
import br.com.samuellna.todo_api.utils.StatusTask;
import jakarta.validation.constraints.AssertTrue;
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

    @AssertTrue(message = "Pelo menos um campo deve ser informado")
    public boolean isValid() {
        return title != null || description != null || status != null;
    }
}
