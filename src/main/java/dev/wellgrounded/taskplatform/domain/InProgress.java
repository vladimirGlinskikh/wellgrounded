package dev.wellgrounded.taskplatform.domain;

public final class InProgress implements TaskStatus {
    private final String processor;
    
    public InProgress(String processor) {
        this.processor = processor;
    }
    
    @Override
    public String description() {
        return "В обработке процессором: " + processor;
    }
}
