package com.timemanagement;

import java.time.LocalDateTime;

// Task Model
public class Task {
    private final String title;
    private final String description;
    private final LocalDateTime dueDate;
    private final int priority;
    private boolean completed;

    public Task(String title, String description, LocalDateTime dueDate, int priority) {
        validateTaskInput(title, description, dueDate, priority);
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = false;
    }

    private void validateTaskInput(String title, String description, 
                                 LocalDateTime dueDate, int priority) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (dueDate == null) {
            throw new IllegalArgumentException("Due date cannot be null");
        }
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 1 and 5");
        }
    }

    // Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDateTime getDueDate() { return dueDate; }
    public int getPriority() { return priority; }
    public boolean isCompleted() { return completed; }

    // Setters
    public void setCompleted(boolean completed) { this.completed = completed; }
}

// Goal Model
public class Goal {
    private final String title;
    private final String description;
    private final LocalDateTime targetDate;
    private int progress;
    private boolean achieved;

    public Goal(String title, String description, LocalDateTime targetDate) {
        validateGoalInput(title, description, targetDate);
        this.title = title;
        this.description = description;
        this.targetDate = targetDate;
        this.progress = 0;
        this.achieved = false;
    }

    private void validateGoalInput(String title, String description, 
                                 LocalDateTime targetDate) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (targetDate == null) {
            throw new IllegalArgumentException("Target date cannot be null");
        }
    }

    // Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDateTime getTargetDate() { return targetDate; }
    public int getProgress() { return progress; }
    public boolean isAchieved() { return achieved; }

    // Setters
    public void setProgress(int progress) {
        if (progress < 0 || progress > 100) {
            throw new IllegalArgumentException("Progress must be between 0 and 100");
        }
        this.progress = progress;
        this.achieved = progress == 100;
    }
}
