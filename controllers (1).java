package com.timemanagement.controller;

import com.timemanagement.model.*;
import com.timemanagement.util.ErrorHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MainController {
    @FXML private TableView<Task> taskTable;
    @FXML private TableView<Goal> goalTable;
    @FXML private TextField taskTitleField;
    @FXML private TextArea taskDescField;
    @FXML private DatePicker taskDueDatePicker;
    @FXML private TextField goalTitleField;
    @FXML private TextArea goalDescField;
    @FXML private DatePicker goalTargetDatePicker;
    
    private ObservableList<Task> tasks = FXCollections.observableArrayList();
    private ObservableList<Goal> goals = FXCollections.observableArrayList();
    
    @FXML
    public void initialize() {
        setupTaskTable();
        setupGoalTable();
        setupEventHandlers();
    }
    
    private void setupTaskTable() {
        TableColumn<Task, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        
        TableColumn<Task, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        
        taskTable.getColumns().addAll(titleCol, statusCol);
        taskTable.setItems(tasks);
    }
    
    private void setupGoalTable() {
        TableColumn<Goal, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        
        TableColumn<Goal, String> progressCol = new TableColumn<>("Progress");
        progressCol.setCellValueFactory(cellData -> cellData.getValue().progressProperty());
        
        goalTable.getColumns().addAll(titleCol, progressCol);
        goalTable.setItems(goals);
    }
    
    @FXML
    private void handleAddTask() {
        try {
            validateTaskInput();
            
            Task task = new Task(
                taskTitleField.getText(),
                taskDescField.getText(),
                taskDueDatePicker.getValue()
            );
            
            tasks.add(task);
            clearTaskFields();
            
        } catch (ValidationException e) {
            ErrorHandler.showError("Invalid Input", "Failed to add task", e.getMessage());
        }
    }
    
    @FXML
    private void handleAddGoal() {
        try {
            validateGoalInput();
            
            Goal goal = new Goal(
                goalTitleField.getText(),
                goalDescField.getText(),
                goalTargetDatePicker.getValue()
            );
            
            goals.add(goal);
            clearGoalFields();
            
        } catch (ValidationException e) {
            ErrorHandler.showError("Invalid Input", "Failed to add goal", e.getMessage());
        }
    }
    
    private void validateTaskInput() throws ValidationException {
        if (taskTitleField.getText().trim().isEmpty()) {
            throw new ValidationException("Task title is required");
        }
        if (taskDueDatePicker.getValue() == null) {
            throw new ValidationException("Due date is required");
        }
    }
    
    private void validateGoalInput() throws ValidationException {
        if (goalTitleField.getText().trim().isEmpty()) {
            throw new ValidationException("Goal title is required");
        }
        if (goalTargetDatePicker.getValue() == null) {
            throw new ValidationException("Target date is required");
        }
    }
}
