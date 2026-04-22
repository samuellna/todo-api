package br.com.samuellna.todo_api.controller;

import br.com.samuellna.todo_api.database.model.TaskEntity;
import br.com.samuellna.todo_api.dto.task.ResponseTaskDto;
import br.com.samuellna.todo_api.dto.task.TaskDto;
import br.com.samuellna.todo_api.dto.task.UpdateTaskDto;
import br.com.samuellna.todo_api.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @Operation(summary = "Busca todas as atividades no banco de dados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de atividades retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public ResponseEntity<List<TaskEntity>> findAll() {
        List<TaskEntity> tasks = taskService.findAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @Operation(summary = "Busca uma atividade")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Atividade encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Atividade não encontrada")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseTaskDto> findById(@PathVariable("id") Long id) {
        ResponseTaskDto task = taskService.findById(id);
        return new ResponseEntity<>(task, HttpStatus.FOUND);
    }

    @Operation(summary = "Busca todas as atividades de um usuário específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Atividades encontradas com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping(value = "/{userId}/user")
    public ResponseEntity<List<TaskEntity>> findByUserId(@PathVariable("userId") Long userId) {
        List<TaskEntity> tasks = taskService.findByUserId(userId);
        return new ResponseEntity<>(tasks, HttpStatus.FOUND);
    }

    @Operation(summary = "Cria uma atividade")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Atividade criada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<TaskEntity> create(@RequestBody
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do usuário", required = true)
        @Valid TaskDto taskDto) {
        TaskEntity task = taskService.create(taskDto);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza uma atividade")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Atividade atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Atividade não encontrada")
    })
    @PatchMapping(value = "/{id}")
    public ResponseEntity<ResponseTaskDto> update(@PathVariable("id") Long id,
                                             @RequestBody @Valid UpdateTaskDto taskDto) {
        ResponseTaskDto task = taskService.update(id, taskDto);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @Operation(summary = "Remove uma atividade")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Atividade removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Atividade não encontrada")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        taskService.delete(id);
        return new ResponseEntity<>("Atividade removida.", HttpStatus.OK);
    }
}
