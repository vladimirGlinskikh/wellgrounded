package dev.wellgrounded.taskplatform.application;

import dev.wellgrounded.taskplatform.domain.Task;
import dev.wellgrounded.taskplatform.domain.TaskStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TaskService {

    private final Map<String, Task> tasks = new ConcurrentHashMap<>();

    public List<Task> getAllTasks() {
        return List.copyOf(tasks.values());
    }

    public Task createTask(String title, String description, dev.wellgrounded.taskplatform.domain.TaskPriority priority) {
        String id = UUID.randomUUID().toString();
        Task task = new Task(
                id,
                title,
                description,
                new dev.wellgrounded.taskplatform.domain.Pending(),
                priority,
                Instant.now(),
                Instant.now()
        );
        tasks.put(id, task);
        return task;
    }

    public Task updateStatus(String id, TaskStatus newStatus) {
        Task existing = tasks.get(id);
        if (existing == null) {
            throw new IllegalArgumentException("Task not found");
        }
        Task updated = existing.withStatus(newStatus);
        tasks.put(id, updated);
        return updated;
    }
}