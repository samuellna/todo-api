package br.com.samuellna.todo_api.service;

import br.com.samuellna.todo_api.database.model.TaskEntity;
import br.com.samuellna.todo_api.database.model.UserEntity;
import br.com.samuellna.todo_api.database.repository.TaskRepository;
import br.com.samuellna.todo_api.database.repository.UserRepository;
import br.com.samuellna.todo_api.dto.user.ResponseUserDto;
import br.com.samuellna.todo_api.dto.user.UpdateUserDto;
import br.com.samuellna.todo_api.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Optional<ResponseUserDto> findById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isEmpty()) return Optional.empty();
        List<TaskEntity> tasks = taskRepository.findByUserId(id);
        return Optional.of(new ResponseUserDto(
            user.get().getId(),
            user.get().getName(),
            user.get().getEmail(),
            tasks
        ));
    }

    public UserEntity create(UserDto userDto) {
        Long id = userRepository.create(userDto);
        return new UserEntity(
            id,
            userDto.getName(),
            userDto.getEmail()
        );
    }

    public Optional<ResponseUserDto> update(Long id, UpdateUserDto userDto) {
        // Checks if the body is empty
        if(userDto.getEmail() == null && userDto.getName() == null) return Optional.empty();

        Optional<ResponseUserDto> user = this.findById(id);
        if(user.isEmpty()) return Optional.empty(); // The user doesn't exist in the database.

        userRepository.update(id, userDto);
        return this.findById(id);
    }

    public void delete(Long id) {
        Optional<ResponseUserDto> user = this.findById(id);
        if(user.isEmpty()) return; // The user doesn't exist in the database.
        userRepository.delete(id);
    }
}
