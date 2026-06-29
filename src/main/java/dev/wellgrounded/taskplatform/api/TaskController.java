package dev.wellgrounded.taskplatform.api;

import dev.wellgrounded.taskplatform.application.TaskService;
import dev.wellgrounded.taskplatform.domain.TaskPriority;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tasks", description = "Управление задачами")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Получить все задачи", description = "Возвращает список всех задач")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Успешно")
    })
    @GetMapping
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @Operation(summary = "Создать новую задачу")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Задача успешно создана"),
        @ApiResponse(responseCode = "400", description = "Некорректные данные")
    })
    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody CreateTaskRequest request) {
        TaskDto task = taskService.createTask(
                request.title(),
                request.description(),
                request.priority()
        );
        return ResponseEntity.ok(task);
    }

    @Operation(summary = "Обновить статус задачи")
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