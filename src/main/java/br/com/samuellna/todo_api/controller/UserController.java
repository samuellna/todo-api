package br.com.samuellna.todo_api.controller;

import br.com.samuellna.todo_api.database.model.UserEntity;
import br.com.samuellna.todo_api.dto.UpdateUserDto;
import br.com.samuellna.todo_api.dto.UserDto;
import br.com.samuellna.todo_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<UserEntity> findById(@PathVariable("id") Long id) {
        Optional<UserEntity> user = userService.findById(id);
        return user
                .map(userEntity -> new ResponseEntity<>(userEntity, HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody @Valid UserDto userDto) {
        UserEntity user = userService.create(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<UserEntity> update(
        @PathVariable("id") Long id,
        @RequestBody @Valid UpdateUserDto userDto
    ) {
        Optional<UserEntity> user = userService.update(id, userDto);
        return user
                .map(userEntity -> new ResponseEntity<>(userEntity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>("Usuário removido.", HttpStatus.OK);
    }
}
