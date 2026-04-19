package br.com.samuellna.todo_api.controller;

import br.com.samuellna.todo_api.database.model.UserEntity;
import br.com.samuellna.todo_api.dto.user.ResponseUserDto;
import br.com.samuellna.todo_api.dto.user.UpdateUserDto;
import br.com.samuellna.todo_api.dto.user.UserDto;
import br.com.samuellna.todo_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> findAll() {
        List<UserEntity> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseUserDto> findById(@PathVariable("id") Long id) {
        ResponseUserDto user = userService.findById(id);
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody @Valid UserDto userDto) {
        UserEntity user = userService.create(userDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<ResponseUserDto> update(
        @PathVariable("id") Long id,
        @RequestBody @Valid UpdateUserDto userDto
    ) {
        ResponseUserDto user = userService.update(id, userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>("Usuário removido.", HttpStatus.OK);
    }
}
