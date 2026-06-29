package dev.wellgrounded.taskplatform.api;

import dev.wellgrounded.taskplatform.application.TaskService;
import dev.wellgrounded.taskplatform.domain.TaskPriority;
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
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody CreateTaskRequest request) {
        TaskDto task = taskService.createTask(
                request.title(), 
                request.description(), 
                request.priority()
        );
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskDto> updateStatus(
            @PathVariable String id, 
            @RequestBody String status) {

        TaskDto updated = taskService.updateStatus(id, new dev.wellgrounded.taskplatform.domain.Pending());
        return ResponseEntity.ok(updated);
    }
}

record CreateTaskRequest(
        String title,
        String description,
        TaskPriority priority
) {}