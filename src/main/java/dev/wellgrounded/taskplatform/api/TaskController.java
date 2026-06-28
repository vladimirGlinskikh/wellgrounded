package dev.wellgrounded.taskplatform.api;

import dev.wellgrounded.taskplatform.domain.Task;
import dev.wellgrounded.taskplatform.domain.TaskStatus;
import dev.wellgrounded.taskplatform.application.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskRequest request) {
        Task task = taskService.createTask(request.title(), request.description(), request.priority());
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Task> updateStatus(@PathVariable String id, @RequestBody TaskStatus status) {
        Task updated = taskService.updateStatus(id, status);
        return ResponseEntity.ok(updated);
    }
}

record CreateTaskRequest(String title, String description, dev.wellgrounded.taskplatform.domain.TaskPriority priority) {}