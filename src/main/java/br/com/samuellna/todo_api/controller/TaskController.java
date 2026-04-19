package br.com.samuellna.todo_api.controller;

import br.com.samuellna.todo_api.database.model.TaskEntity;
import br.com.samuellna.todo_api.dto.task.ResponseTaskDto;
import br.com.samuellna.todo_api.dto.task.TaskDto;
import br.com.samuellna.todo_api.dto.task.UpdateTaskDto;
import br.com.samuellna.todo_api.service.TaskService;
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

    @GetMapping
    public ResponseEntity<List<TaskEntity>> findAll() {
        List<TaskEntity> tasks = taskService.findAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseTaskDto> findById(@PathVariable("id") Long id) {
        ResponseTaskDto task = taskService.findById(id);
        return new ResponseEntity<>(task, HttpStatus.FOUND);
    }

    @GetMapping(value = "/{userId}/user")
    public ResponseEntity<List<TaskEntity>> findByUserId(@PathVariable("userId") Long userId) {
        List<TaskEntity> tasks = taskService.findByUserId(userId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskEntity> create(@RequestBody @Valid TaskDto taskDto) {
        TaskEntity task = taskService.create(taskDto);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<ResponseTaskDto> update(@PathVariable("id") Long id,
                                             @RequestBody @Valid UpdateTaskDto taskDto) {
        ResponseTaskDto task = taskService.update(id, taskDto);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        taskService.delete(id);
        return new ResponseEntity<>("Atividade removida.", HttpStatus.OK);
    }
}
