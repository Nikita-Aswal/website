package com.timemanagement.dao;

import com.timemanagement.model.Task;
import com.timemanagement.model.Goal;
import org.junit.jupiter.api.*;
import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TaskDAOTest {
    private TaskDAO taskDAO;
    private DataSource dataSource;

    @BeforeEach
    void setUp() {
        dataSource = createTestDataSource();
        taskDAO = new TaskDAO(dataSource);
    }

    @Test
    @DisplayName("Should save task successfully")
    void saveTask() {
        // Arrange
        Task task = new Task("Test Task", "Description", LocalDateTime.now().plusDays(1), 1);

        // Act
        Task savedTask = taskDAO.save(task);

        // Assert
        assertNotNull(savedTask.getId());
        assertEquals("Test Task", savedTask.getTitle());
    }

    @Test
    @DisplayName("Should find task by ID")
    void findTaskById() {
        // Arrange
        Task task = new Task("Test Task", "Description", LocalDateTime.now().plusDays(1), 1);
        Task savedTask = taskDAO.save(task);

        // Act
        Task foundTask = taskDAO.findById(savedTask.getId());

        // Assert
        assertNotNull(foundTask);
        assertEquals(savedTask.getId(), foundTask.getId());
        assertEquals(savedTask.getTitle(), foundTask.getTitle());
    }

    @Test
    @DisplayName("Should find all tasks")
    void findAllTasks() {
        // Arrange
        taskDAO.save(new Task("Task 1", "Description", LocalDateTime.now().plusDays(1), 1));
        taskDAO.save(new Task("Task 2", "Description", LocalDateTime.now().plusDays(2), 2));

        // Act
        List<Task> tasks = taskDAO.findAll();

        // Assert
        assertEquals(2, tasks.size());
    }
}

class GoalDAOTest {
    private GoalDAO goalDAO;
    private DataSource dataSource;

    @BeforeEach
    void setUp() {
        dataSource = createTestDataSource();
        goalDAO = new GoalDAO(dataSource);
    }

    @Test
    @DisplayName("Should save goal successfully")
    void saveGoal() {
        // Arrange
        Goal goal = new Goal("Test Goal", "Description", LocalDateTime.now().plusDays(30));

        // Act
        Goal savedGoal = goalDAO.save(goal);

        // Assert
        assertNotNull(savedGoal.getId());
        assertEquals("Test Goal", savedGoal.getTitle());
    }

    @Test
    @DisplayName("Should update goal")
    void updateGoal() {
        // Arrange
        Goal goal = new Goal("Test Goal", "Description", LocalDateTime.now().plusDays(30));
        Goal savedGoal = goalDAO.save(goal);
        savedGoal.setProgress(50);

        // Act
        Goal updatedGoal = goalDAO.update(savedGoal);

        // Assert
        assertEquals(50, updatedGoal.getProgress());
    }

    @Test
    @DisplayName("Should find goals by status")
    void findGoalsByStatus() {
        // Arrange
        Goal goal1 = new Goal("Goal 1", "Description", LocalDateTime.now().plusDays(30));
        goal1.setProgress(100);
        Goal goal2 = new Goal("Goal 2", "Description", LocalDateTime.now().plusDays(30));
        goalDAO.save(goal1);
        goalDAO.save(goal2);

        // Act
        List<Goal> completedGoals = goalDAO.findByStatus(true);
        List<Goal> inProgressGoals = goalDAO.findByStatus(false);

        // Assert
        assertEquals(1, completedGoals.size());
        assertEquals(1, inProgressGoals.size());
    }

    private DataSource createTestDataSource() {
        // Implementation for creating test database connection
        // This would typically use an in-memory database like H2
        return null;
    }
}
