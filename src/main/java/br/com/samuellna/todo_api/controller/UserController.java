package br.com.samuellna.todo_api.controller;

import br.com.samuellna.todo_api.database.model.UserEntity;
import br.com.samuellna.todo_api.dto.user.ResponseUserDto;
import br.com.samuellna.todo_api.dto.user.UpdateUserDto;
import br.com.samuellna.todo_api.dto.user.UserDto;
import br.com.samuellna.todo_api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "Gerenciamento de usuários")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Listar todos os usuários")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<UserEntity>> findAll() {
        List<UserEntity> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Buscar usuário pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseUserDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Operation(summary = "Criar usuário")
    @PostMapping
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<UserEntity> create(
        @RequestBody
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do usuário", required = true)
        @Valid UserDto userDto
    ) {
        UserEntity user = userService.create(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualizar dados do usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PatchMapping(value = "/{id}")
    public ResponseEntity<ResponseUserDto> update(
        @PathVariable("id") Long id,
        @RequestBody @Valid UpdateUserDto userDto
    ) {
        ResponseUserDto user = userService.update(id, userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "Deletar usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>("Usuário removido.", HttpStatus.OK);
    }
}
