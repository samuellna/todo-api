package br.com.samuellna.todo_api.service;

import br.com.samuellna.todo_api.database.model.TaskEntity;
import br.com.samuellna.todo_api.database.model.UserEntity;
import br.com.samuellna.todo_api.database.repository.TaskRepository;
import br.com.samuellna.todo_api.database.repository.UserRepository;
import br.com.samuellna.todo_api.dto.task.ResponseTaskDto;
import br.com.samuellna.todo_api.dto.task.TaskDto;
import br.com.samuellna.todo_api.dto.task.UpdateTaskDto;
import br.com.samuellna.todo_api.utils.StatusTask;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public List<TaskEntity> findAll() { return taskRepository.findAll(); }

    public Optional<ResponseTaskDto> findById(Long id) {
        Optional<TaskEntity> task = taskRepository.findById(id);
        if(task.isEmpty()) return Optional.empty();
        Optional<UserEntity> user = userRepository.findById(task.get().getUserId());
        return user.map(userEntity -> new ResponseTaskDto(
            id,
            task.get().getTitle(),
            task.get().getDescription(),
            task.get().getStatus(),
            task.get().getCreatedAt(),
            userEntity
        ));
    }

    public List<TaskEntity> findByUserId(Long id) { return taskRepository.findByUserId(id); }

    public TaskEntity create(TaskDto taskDto) {
        Long id = taskRepository.create(taskDto);
        return new TaskEntity(
            id,
            taskDto.getTitle(),
            taskDto.getDescription(),
            StatusTask.PENDING,
            Timestamp.valueOf(LocalDateTime.now()),
            taskDto.getUserId()
        );
    }

    public Optional<ResponseTaskDto> update(Long id, UpdateTaskDto taskDto) {
        if(taskDto.getDescription() == null
            && taskDto.getTitle() == null
            && taskDto.getStatus() == null) return Optional.empty();

        Optional<ResponseTaskDto> task = this.findById(id);
        if(task.isEmpty()) return Optional.empty();

        taskRepository.update(id, taskDto);
        return this.findById(id);
    }

    public void delete(Long id) {
        Optional<ResponseTaskDto> task = this.findById(id);
        if(task.isEmpty()) return;
        taskRepository.delete(id);
    }
}
