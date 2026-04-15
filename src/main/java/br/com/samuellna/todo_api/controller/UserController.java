package br.com.samuellna.todo_api.controller;

import br.com.samuellna.todo_api.database.model.UserEntity;
import br.com.samuellna.todo_api.dto.user.UserDto;
import br.com.samuellna.todo_api.service.UserService;
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
        if(user.isEmpty()) {
            return new ResponseEntity<UserEntity>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserEntity>(user.get(), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody UserDto userDto) {
        UserEntity user = userService.create(userDto);
        return new ResponseEntity<UserEntity>(user, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<String> update(
        @PathVariable("id") Long id,
        @RequestBody UserDto userDto
    ) {
        String msg = userService.update(id, userDto);
        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }
}
