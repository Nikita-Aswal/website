package com.timemanagement.controller;

import com.timemanagement.model.Task;
import com.timemanagement.model.Goal;
import com.timemanagement.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        // Implementation
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = findTaskById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        return ResponseEntity.ok(task);
    }
    
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        // Implementation
        return ResponseEntity.ok(savedTask);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody Task task) {
        Task existingTask = findTaskById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        // Update logic
        return ResponseEntity.ok(updatedTask);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        Task task = findTaskById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        // Delete logic
        return ResponseEntity.ok().build();
    }
}

@RestController
@RequestMapping("/api/goals")
public class GoalController {
    
    @GetMapping
    public ResponseEntity<List<Goal>> getAllGoals() {
        // Implementation
        return ResponseEntity.ok(goals);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Goal> getGoalById(@PathVariable Long id) {
        Goal goal = findGoalById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Goal not found with id: " + id));
        return ResponseEntity.ok(goal);
    }
    
    @PostMapping
    public ResponseEntity<Goal> createGoal(@Valid @RequestBody Goal goal) {
        // Implementation
        return ResponseEntity.ok(savedGoal);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Goal> updateGoal(@PathVariable Long id, @Valid @RequestBody Goal goal) {
        Goal existingGoal = findGoalById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Goal not found with id: " + id));
        // Update logic
        return ResponseEntity.ok(updatedGoal);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGoal(@PathVariable Long id) {
        Goal goal = findGoalById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Goal not found with id: " + id));
        // Delete logic
        return ResponseEntity.ok().build();
    }
}
