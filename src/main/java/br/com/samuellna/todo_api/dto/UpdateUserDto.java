package br.com.samuellna.todo_api.dto;
import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateUserDto {
    private String name;

    @Email(message = "O email deve ser válido")
    private String email;
}
