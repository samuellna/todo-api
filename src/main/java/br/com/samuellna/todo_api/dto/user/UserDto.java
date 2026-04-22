package br.com.samuellna.todo_api.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "DTO para criação de usuários")
public class UserDto {
    @Schema(description = "Nome do usuário", example = "Samuel")
    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @Schema(description = "Email do usuário", example = "samuel@gmail.com")
    @Email(message = "Email inválido")
    @NotBlank(message = "O email é obrigatório")
    private String email;
}
