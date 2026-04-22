package br.com.samuellna.todo_api.infra;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Resposta padrão de erro")
public class RestErrorResponse {
    @Schema(example = "BAD_REQUEST")
    private HttpStatus status;
    @Schema(example = "Email inválido")
    private String message;
}
