package dev.wellgrounded.taskplatform.domain;

public record Task(
        String id,
        String title,
        String description,
        TaskStatus status,
        TaskPriority priority,
        java.time.Instant createdAt,
        java.time.Instant updatedAt
) {

    public Task {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be blank");
        }
    }

    public Task withStatus(TaskStatus newStatus) {
        return new Task(id, title, description, newStatus, priority, createdAt, java.time.Instant.now());
    }
}