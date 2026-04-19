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
import java.util.Optional;

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
        Optional<ResponseTaskDto> task = taskService.findById(id);
        return task
                .map(t -> new ResponseEntity<>(t, HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
        Optional<ResponseTaskDto> task = taskService.update(id, taskDto);
        return task
                .map(t -> new ResponseEntity<>(t, HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        taskService.delete(id);
        return new ResponseEntity<>("Atividade removida.", HttpStatus.OK);
    }
}
