package com.timemanagement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// Main Application Class
public class TimeManagementApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final TaskManager taskManager = new TaskManager();
    private static final GoalManager goalManager = new GoalManager();
    
    public static void main(String[] args) {
        while (true) {
            try {
                displayMainMenu();
                int choice = getUserChoice(1, 7);
                processMainMenuChoice(choice);
            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage());
                System.out.println("Please try again.\n");
                scanner.nextLine(); // Clear scanner buffer
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n=== Time Management System ===");
        System.out.println("1. Add New Task");
        System.out.println("2. View All Tasks");
        System.out.println("3. Add New Goal");
        System.out.println("4. View All Goals");
        System.out.println("5. Mark Task as Complete");
        System.out.println("6. Update Goal Progress");
        System.out.println("7. Exit");
        System.out.print("Enter your choice (1-7): ");
    }

    private static void processMainMenuChoice(int choice) {
        switch (choice) {
            case 1 -> addNewTask();
            case 2 -> viewAllTasks();
            case 3 -> addNewGoal();
            case 4 -> viewAllGoals();
            case 5 -> markTaskComplete();
            case 6 -> updateGoalProgress();
            case 7 -> {
                System.out.println("Thank you for using Time Management System!");
                System.exit(0);
            }
        }
    }

    private static int getUserChoice(int min, int max) {
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            if (choice < min || choice > max) {
                throw new IllegalArgumentException(
                    String.format("Please enter a number between %d and %d", min, max));
            }
            return choice;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please enter a valid number");
        }
    }

    private static LocalDateTime getDateTimeInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return LocalDateTime.parse(input, 
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            } catch (Exception e) {
                System.out.println("Invalid date format. Use yyyy-MM-dd HH:mm");
            }
        }
    }

    // Task-related methods
    private static void addNewTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine().trim();
        
        System.out.print("Enter task description: ");
        String description = scanner.nextLine().trim();
        
        LocalDateTime dueDate = getDateTimeInput(
            "Enter due date (yyyy-MM-dd HH:mm): ");
        
        System.out.print("Enter priority (1-5): ");
        int priority = getUserChoice(1, 5);
        
        Task task = new Task(title, description, dueDate, priority);
        taskManager.addTask(task);
        System.out.println("Task added successfully!");
    }

    private static void viewAllTasks() {
        List<Task> tasks = taskManager.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        
        System.out.println("\n=== All Tasks ===");
        tasks.forEach(task -> {
            System.out.println("\nTitle: " + task.getTitle());
            System.out.println("Description: " + task.getDescription());
            System.out.println("Due Date: " + 
                task.getDueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            System.out.println("Priority: " + task.getPriority());
            System.out.println("Status: " + 
                (task.isCompleted() ? "Completed" : "Pending"));
            System.out.println("-------------------");
        });
    }

    private static void markTaskComplete() {
        List<Task> tasks = taskManager.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks available to mark as complete.");
            return;
        }

        System.out.println("\n=== Mark Task as Complete ===");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i).getTitle());
        }

        System.out.print("Enter task number to mark as complete: ");
        int taskIndex = getUserChoice(1, tasks.size()) - 1;
        taskManager.markTaskComplete(taskIndex);
        System.out.println("Task marked as complete!");
    }

    // Goal-related methods
    private static void addNewGoal() {
        System.out.print("Enter goal title: ");
        String title = scanner.nextLine().trim();
        
        System.out.print("Enter goal description: ");
        String description = scanner.nextLine().trim();
        
        LocalDateTime targetDate = getDateTimeInput(
            "Enter target date (yyyy-MM-dd HH:mm): ");
        
        Goal goal = new Goal(title, description, targetDate);
        goalManager.addGoal(goal);
        System.out.println("Goal added successfully!");
    }

    private static void viewAllGoals() {
        List<Goal> goals = goalManager.getAllGoals();
        if (goals.isEmpty()) {
            System.out.println("No goals found.");
            return;
        }
        
        System.out.println("\n=== All Goals ===");
        goals.forEach(goal -> {
            System.out.println("\nTitle: " + goal.getTitle());
            System.out.println("Description: " + goal.getDescription());
            System.out.println("Target Date: " + 
                goal.getTargetDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            System.out.println("Progress: " + goal.getProgress() + "%");
            System.out.println("Status: " + 
                (goal.isAchieved() ? "Achieved" : "In Progress"));
            System.out.println("-------------------");
        });
    }

    private static void updateGoalProgress() {
        List<Goal> goals = goalManager.getAllGoals();
        if (goals.isEmpty()) {
            System.out.println("No goals available to update.");
            return;
        }

        System.out.println("\n=== Update Goal Progress ===");
        for (int i = 0; i < goals.size(); i++) {
            System.out.printf("%d. %s (Current Progress: %d%%)%n", 
                i + 1, goals.get(i).getTitle(), goals.get(i).getProgress());
        }

        System.out.print("Enter goal number to update: ");
        int goalIndex = getUserChoice(1, goals.size()) - 1;
        
        System.out.print("Enter new progress (0-100): ");
        int progress = getUserChoice(0, 100);
        
        goalManager.updateGoalProgress(goalIndex, progress);
        System.out.println("Goal progress updated successfully!");
    }
}
