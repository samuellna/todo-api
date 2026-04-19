package br.com.samuellna.todo_api.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class RestErrorResponse {
    private HttpStatus status;
    private String message;
}
