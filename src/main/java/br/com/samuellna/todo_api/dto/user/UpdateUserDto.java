package br.com.samuellna.todo_api.dto.user;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "DTO para atualização de usuário")
public class UpdateUserDto {
    @Schema(description = "Nome do usuário")
    private String name;

    @Schema(description = "Email do usuário")
    @Email(message = "O email deve ser válido")
    private String email;

    @AssertTrue(message = "Pelo menos um campo deve ser informado")
    public boolean isValid() {
        return name != null || email != null;
    }
}
