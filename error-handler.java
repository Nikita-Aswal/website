package com.timemanagement.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorHandler {
    
    public static void showError(String title, String header, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    public static void showWarning(String title, String header, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}
