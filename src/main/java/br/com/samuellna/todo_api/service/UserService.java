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

    public String update(Long id, UserDto userDto) {
        Optional<UserEntity> user = this.findById(id);

        if(user.isEmpty()) return null;
        if(userDto.getName() != null) {
            user.get().setName(userDto.getName());
        }
        if(userDto.getEmail() != null) {
            user.get().setEmail(userDto.getEmail());
        }

        userRepository.update(id, userDto);
        return "Usuário atualizado";
    }
}
