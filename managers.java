package com.timemanagement;

import java.util.ArrayList;
import java.util.List;

// Task Manager
public class TaskManager {
    private final List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }
        tasks.add(task);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public void markTaskComplete(int index) {
        validateIndex(index);
        tasks.get(index).setCompleted(true);
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Invalid task index");
        }
    }
}

// Goal Manager
public class GoalManager {
    private final List<Goal> goals;

    public GoalManager() {
        this.goals = new ArrayList<>();
    }

    public void addGoal(Goal goal) {
        if (goal == null) {
            throw new IllegalArgumentException("Goal cannot be null");
        }
        goals.add(goal);
    }

    public List<Goal> getAllGoals() {
        return new ArrayList<>(goals);
    }

    public void updateGoalProgress(int index, int progress) {
        validateIndex(index);
        goals.get(index).setProgress(progress);
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= goals.size()) {
            throw new IllegalArgumentException("Invalid goal index");
        }
    }
}
