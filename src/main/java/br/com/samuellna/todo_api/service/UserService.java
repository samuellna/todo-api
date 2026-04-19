package br.com.samuellna.todo_api.service;

import br.com.samuellna.todo_api.database.model.TaskEntity;
import br.com.samuellna.todo_api.database.model.UserEntity;
import br.com.samuellna.todo_api.database.repository.TaskRepository;
import br.com.samuellna.todo_api.database.repository.UserRepository;
import br.com.samuellna.todo_api.dto.user.ResponseUserDto;
import br.com.samuellna.todo_api.dto.user.UpdateUserDto;
import br.com.samuellna.todo_api.dto.user.UserDto;
import br.com.samuellna.todo_api.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public ResponseUserDto findById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        List<TaskEntity> tasks = taskRepository.findByUserId(id);
        return new ResponseUserDto(
            user.getId(),
            user.getName(),
            user.getEmail(),
            tasks
        );
    }

    public UserEntity create(UserDto userDto) {
        Long id = userRepository.create(userDto);
        return new UserEntity(
            id,
            userDto.getName(),
            userDto.getEmail()
        );
    }

    public ResponseUserDto update(Long id, UpdateUserDto userDto) {
        ResponseUserDto user = this.findById(id);
        userRepository.update(user.getId(), userDto);
        return this.findById(id);
    }

    public void delete(Long id) {
        ResponseUserDto user = this.findById(id);
        userRepository.delete(user.getId());
    }
}
