package br.com.samuellna.todo_api.database.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEntity {
    private int id;
    private String name;
    private String email;
}
