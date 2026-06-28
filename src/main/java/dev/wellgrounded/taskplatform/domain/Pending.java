package dev.wellgrounded.taskplatform.domain;

public final class Pending implements TaskStatus {
    @Override
    public String description() {
        return "Задача ожидает обработки";
    }
}
