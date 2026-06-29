package dev.wellgrounded.taskplatform.api;

import dev.wellgrounded.taskplatform.domain.TaskPriority;

import java.time.Instant;

public record TaskDto(
        String id,
        String title,
        String description,
        String status,
        TaskPriority priority,
        Instant createdAt,
        Instant updatedAt
) {

}