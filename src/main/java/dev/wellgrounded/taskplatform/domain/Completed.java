package dev.wellgrounded.taskplatform.domain;

public final class Completed implements TaskStatus {
    @Override
    public String description() {
        return "Задача успешно завершена";
    }
}
