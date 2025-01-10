package com.timemanagement.service;

import com.timemanagement.dao.TaskDAO;
import com.timemanagement.dao.GoalDAO;
import com.timemanagement.model.Task;
import com.timemanagement.model.Goal;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {
    @Mock
    private TaskDAO taskDAO;
    private TaskService taskService;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        taskService = new TaskService(taskDAO);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Should create task successfully")
    void createTaskSuccess() {
        // Arrange
        Task task = new Task("Test Task", "Description", LocalDateTime.now().plusDays(1), 1);
        when(taskDAO.save(any(Task.class))).thenReturn(task);

        // Act
        Task createdTask = taskService.createTask(task);

        // Assert
        assertNotNull(createdTask);
        assertEquals("Test Task", createdTask.getTitle());
        verify(taskDAO).save(task);
    }

    @Test
    @DisplayName("Should throw exception when creating task with past due date")
    void createTaskPastDueDate() {
        // Arrange
        Task task = new Task("Test Task", "Description", LocalDateTime.now().minusDays(1), 1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> taskService.createTask(task));
        verify(taskDAO, never()).save(any());
    }

    @Test
    @DisplayName("Should mark task as complete")
    void markTaskComplete() {
        // Arrange
        Long taskId = 1L;
        Task task = new Task("Test Task", "Description", LocalDateTime.now().plusDays(1), 1);
        when(taskDAO.findById(taskId)).thenReturn(task);

        // Act
        taskService.markTaskComplete(taskId);

        // Assert
        assertTrue(task.isCompleted());
        verify(taskDAO).update(task);
    }
}

class GoalServiceTest {
    @Mock
    private GoalDAO goalDAO;
    private GoalService goalService;
    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        goalService = new GoalService(goalDAO);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Should create goal successfully")
    void createGoalSuccess() {
        // Arrange
        Goal goal = new Goal("Test Goal", "Description", LocalDateTime.now().plusDays(30));
        when(goalDAO.save(any(Goal.class))).thenReturn(goal);

        // Act
        Goal createdGoal = goalService.createGoal(goal);

        // Assert
        assertNotNull(createdGoal);
        assertEquals("Test Goal", createdGoal.getTitle());
        verify(goalDAO).save(goal);
    }

    @Test
    @DisplayName("Should update goal progress")
    void updateGoalProgress() {
        // Arrange
        Long goalId = 1L;
        Goal goal = new Goal("Test Goal", "Description", LocalDateTime.now().plusDays(30));
        when(goalDAO.findById(goalId)).thenReturn(goal);

        // Act
        goalService.updateProgress(goalId, 50);

        // Assert
        assertEquals(50, goal.getProgress());
        verify(goalDAO).update(goal);
    }

    @Test
    @DisplayName("Should throw exception when updating progress with invalid value")
    void updateGoalProgressInvalid() {
        // Arrange
        Long goalId = 1L;
        Goal goal = new Goal("Test Goal", "Description", LocalDateTime.now().plusDays(30));
        when(goalDAO.findById(goalId)).thenReturn(goal);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> goalService.updateProgress(goalId, 150));
        verify(goalDAO, never()).update(any());
    }
}
