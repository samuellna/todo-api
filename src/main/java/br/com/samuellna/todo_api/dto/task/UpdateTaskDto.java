package br.com.samuellna.todo_api.dto.task;
import br.com.samuellna.todo_api.utils.StatusTask;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "DTO para atualização de atividade")
public class UpdateTaskDto {
    @Schema(description = "Título da atividade")
    private String title;
    @Schema(description = "Descrição da atividade")
    private String description;
    @Schema(description = "Status da atividade", example = "DONE")
    private StatusTask status;

    @AssertTrue(message = "Pelo menos um campo deve ser informado")
    public boolean isValid() {
        return title != null || description != null || status != null;
    }
}
