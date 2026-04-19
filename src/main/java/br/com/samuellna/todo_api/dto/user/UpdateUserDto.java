package br.com.samuellna.todo_api.dto.user;
import jakarta.validation.constraints.AssertTrue;
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

    @AssertTrue(message = "Pelo menos um campo deve ser informado")
    public boolean isValid() {
        return name != null || email != null;
    }
}
