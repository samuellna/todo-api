package br.com.samuellna.todo_api.dto.task;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "DTO para criação de atividade")
public class TaskDto {
    @Schema(description = "Título da atividade", example = "Refatoração tela")
    @NotBlank(message = "O título é obrigatório")
    private String title;

    @Schema(description = "Descrição da atividade",
            example = "Reposicionar componentes na tela de acordo com X especificações")
    @NotBlank(message = "A descrição é obrigatória")
    private String description;

    @Schema(description = "ID do usuário que criou a atividade")
    @NotNull(message = "O id do usuário é obrigatório")
    private Long userId;
}
