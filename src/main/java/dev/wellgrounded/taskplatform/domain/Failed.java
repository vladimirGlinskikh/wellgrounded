package dev.wellgrounded.taskplatform.domain;

public final class Failed implements TaskStatus {
    private final String reason;
    
    public Failed(String reason) {
        this.reason = reason;
    }
    
    @Override
    public String description() {
        return "Ошибка: " + reason;
    }
}
