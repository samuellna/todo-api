package br.com.samuellna.todo_api.controller;

import br.com.samuellna.todo_api.database.model.UserEntity;
import br.com.samuellna.todo_api.dto.user.CreateUserDto;
import br.com.samuellna.todo_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> findAll() {
        List<UserEntity> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<String> getUserById(@PathVariable("id") String id) {
        return ResponseEntity.ok("Oi " + id + "!");
    }

    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody CreateUserDto userDto) {
        UserEntity user = userService.create(userDto);
        return new ResponseEntity<UserEntity>(user, HttpStatus.CREATED);
    }
}
