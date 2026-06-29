package dev.wellgrounded.taskplatform.application;

import dev.wellgrounded.taskplatform.api.TaskDto;
import dev.wellgrounded.taskplatform.domain.*;
import dev.wellgrounded.taskplatform.infrastructure.TaskEntity;
import dev.wellgrounded.taskplatform.infrastructure.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<TaskDto> getAllTasks() {
        return repository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public TaskDto createTask(String title, String description, TaskPriority priority) {
        String id = UUID.randomUUID().toString();

        var entity = new TaskEntity();
        entity.setId(id);
        entity.setTitle(title);
        entity.setDescription(description);
        entity.setStatus("PENDING");
        entity.setPriority(priority.name());
        entity.setCreatedAt(Instant.now());
        entity.setUpdatedAt(Instant.now());

        var saved = repository.save(entity);
        return toDto(saved);
    }

    public TaskDto updateStatus(String id, TaskStatus newStatus) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found: " + id));

        entity.setStatus(newStatus.getClass().getSimpleName().toUpperCase());
        entity.setUpdatedAt(Instant.now());

        var saved = repository.save(entity);
        return toDto(saved);
    }

    private TaskDto toDto(TaskEntity entity) {
        return new TaskDto(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus(),
                TaskPriority.valueOf(entity.getPriority()),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}