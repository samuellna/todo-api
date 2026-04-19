package br.com.samuellna.todo_api.service;

import br.com.samuellna.todo_api.database.model.TaskEntity;
import br.com.samuellna.todo_api.database.model.UserEntity;
import br.com.samuellna.todo_api.database.repository.TaskRepository;
import br.com.samuellna.todo_api.database.repository.UserRepository;
import br.com.samuellna.todo_api.dto.task.ResponseTaskDto;
import br.com.samuellna.todo_api.dto.task.TaskDto;
import br.com.samuellna.todo_api.dto.task.UpdateTaskDto;
import br.com.samuellna.todo_api.exceptions.TaskNotFoundException;
import br.com.samuellna.todo_api.exceptions.UserNotFoundException;
import br.com.samuellna.todo_api.utils.StatusTask;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public List<TaskEntity> findAll() { return taskRepository.findAll(); }

    public ResponseTaskDto findById(Long id) {
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);

        UserEntity user = userRepository.findById(task.getUserId())
                .orElseThrow(UserNotFoundException::new);

        return new ResponseTaskDto(
            id,
            task.getTitle(),
            task.getDescription(),
            task.getStatus(),
            task.getCreatedAt(),
            user
        );
    }

    public List<TaskEntity> findByUserId(Long id) {
        userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return taskRepository.findByUserId(id);
    }

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

    public ResponseTaskDto update(Long id, UpdateTaskDto taskDto) {
        ResponseTaskDto task = this.findById(id);
        taskRepository.update(task.getId(), taskDto);
        return this.findById(id);
    }

    public void delete(Long id) {
        ResponseTaskDto task = this.findById(id);
        taskRepository.delete(task.getId());
    }
}
