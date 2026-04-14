package br.com.samuellna.todo_api.dto.user;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateUserDto {
    private String name;
    private String email;
}
