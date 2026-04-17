package br.com.samuellna.todo_api.service;

import br.com.samuellna.todo_api.database.model.TaskEntity;
import br.com.samuellna.todo_api.database.repository.TaskRepository;
import br.com.samuellna.todo_api.dto.TaskDto;
import br.com.samuellna.todo_api.dto.UpdateTaskDto;
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

    public List<TaskEntity> findAll() { return taskRepository.findAll(); }

    public Optional<TaskEntity> findById(Long id) { return taskRepository.findById(id); }

    public TaskEntity create(TaskDto taskDto) {
        Long id = taskRepository.create(taskDto);
        return new TaskEntity(
            id,
            taskDto.getTitle(),
            taskDto.getDescription(),
            StatusTask.NOT_STARTED,
            Timestamp.valueOf(LocalDateTime.now())
        );
    }

    public Optional<TaskEntity> update(Long id, UpdateTaskDto taskDto) {
        if(taskDto.getDescription() == null
            && taskDto.getTitle() == null
            && taskDto.getStatus() == null) return Optional.empty();

        Optional<TaskEntity> task = this.findById(id);
        if(task.isEmpty()) return Optional.empty();

        taskRepository.update(id, taskDto);
        return this.findById(id);
    }

    public void delete(Long id) {
        Optional<TaskEntity> task = this.findById(id);
        if(task.isEmpty()) return;
        taskRepository.delete(id);
    }
}
