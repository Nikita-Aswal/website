package com.timemanagement.model;

import lombok.Data;
import javax.validation.constraints.*;

@Data
public class Task {
    @NotNull(message = "Task ID cannot be null")
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @FutureOrPresent(message = "Due date must be in present or future")
    private LocalDateTime dueDate;

    @Min(value = 1, message = "Priority must be between 1 and 5")
    @Max(value = 5, message = "Priority must be between 1 and 5")
    private int priority;

    private boolean completed;
}

@Data
public class Goal {
    @NotNull(message = "Goal ID cannot be null")
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotNull(message = "Target date is required")
    @Future(message = "Target date must be in the future")
    private LocalDateTime targetDate;

    private List<Milestone> milestones;
    private boolean achieved;
}
