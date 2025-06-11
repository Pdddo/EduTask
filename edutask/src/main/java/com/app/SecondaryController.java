package com.app;

import java.io.IOException;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SecondaryController {

    @FXML private TextField descriptionField;
    @FXML private ChoiceBox<String> priorityChoiceBox;
    @FXML private CheckBox hasDeadlineCheckBox;
    @FXML private VBox deadlineContainer;
    @FXML private DatePicker deadlinePicker;

    @FXML
    public void initialize() {
        priorityChoiceBox.getItems().addAll("Low", "Medium", "High");
        priorityChoiceBox.setValue("Medium");

        // Kode di dalam ini akan berjalan setiap kali checkbox di-klik.
        hasDeadlineCheckBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            deadlineContainer.setVisible(isNowSelected);
            deadlineContainer.setManaged(isNowSelected);
        });
    }

    @FXML
    void saveTask() throws IOException {
        String description = descriptionField.getText();
        String priority = priorityChoiceBox.getValue();
        
        if (description == null || description.trim().isEmpty()) {
            showError("Please fill in the task description.");
            return;
        }

        Task newTask;

        if (hasDeadlineCheckBox.isSelected()) {
            LocalDate deadline = deadlinePicker.getValue();
            if (deadline == null) {
                showError("Please select a deadline date.");
                return;
            }
            newTask = new DeadlineTask(description, priority, deadline);
        } else {
            newTask = new SimpleTask(description, priority);
        }

        PrimaryController.addTask(newTask);
        switchToPrimary();
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}