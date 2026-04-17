package br.com.samuellna.todo_api.service;

import br.com.samuellna.todo_api.database.model.UserEntity;
import br.com.samuellna.todo_api.database.repository.UserRepository;
import br.com.samuellna.todo_api.dto.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    public UserEntity create(UserDto userDto) {
        UserEntity user = new UserEntity(
            null,
            userDto.getName(),
            userDto.getEmail()
        );

        Long id = userRepository.create(user);
        user.setId(id);
        return user;
    }

    public Optional<UserEntity> update(Long id, UserDto userDto) {
        // Checks if the body is empty
        if(userDto.getEmail() == null && userDto.getName() == null) return Optional.empty();

        Optional<UserEntity> user = this.findById(id);
        if(user.isEmpty()) return Optional.empty(); // The user doesn't exist in the database.

        userRepository.update(id, userDto);
        return this.findById(id);
    }

    public void delete(Long id) {
        Optional<UserEntity> user = this.findById(id);
        if(user.isEmpty()) return; // The user doesn't exist in the database.
        userRepository.delete(id);
    }
}
