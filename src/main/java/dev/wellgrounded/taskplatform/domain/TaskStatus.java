package dev.wellgrounded.taskplatform.domain;

public sealed interface TaskStatus permits Pending, InProgress, Completed, Failed {

    String description();
}

