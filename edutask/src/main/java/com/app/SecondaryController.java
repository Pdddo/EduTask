package com.app;

import java.io.IOException;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SecondaryController {
    
    @FXML private TextField descriptionField;
    @FXML private ChoiceBox<String> priorityChoiceBox;
    @FXML private CheckBox hasDeadlineCheckBox;
    @FXML private VBox deadlineContainer;
    @FXML private DatePicker deadlinePicker;
    @FXML private Button closeWindow;
    @FXML private Button saveButton;

    @FXML
    public void initialize() {
        priorityChoiceBox.getItems().addAll("Low", "Medium", "High");
        priorityChoiceBox.setValue("Medium");

        // Mengecek setiap kali checkbos diklik
        hasDeadlineCheckBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            deadlineContainer.setVisible(isNowSelected);
            deadlineContainer.setManaged(isNowSelected);
        });
    }

    // Fungsi untuk menyimpan task
    @FXML
    void saveTask() throws IOException {
        String description = descriptionField.getText();
        String priority = priorityChoiceBox.getValue();
        
        if (description == null || description.trim().isEmpty()) {
            showError("Tolong isi deskripsi tugas.");
            return;
        }   

        Task newTask;

        if (hasDeadlineCheckBox.isSelected()) {
            LocalDate deadline = deadlinePicker.getValue();
            if (deadline == null) {
                showError("Mohon pilih tanggal deadline.");
                return;
            }
            newTask = new DeadlineTask(description, priority, deadline);
        } else {
            newTask = new SimpleTask(description, priority);
        }

        PrimaryController.addTask(newTask);
        resetFields();
    }

    // Fungsi untuk reset Textfield
    private void resetFields() {
        descriptionField.clear(); //reset deskripsi
        priorityChoiceBox.setValue("Medium"); //reset prioritas
        deadlinePicker.setValue(null); // reset deadline
        hasDeadlineCheckBox.setSelected(false); // reset chechkbox
    }

    // Fungsi untuk menutup Scene 2
    @FXML
    private void closeWindow() throws IOException {
            Stage stage = (Stage) closeWindow.getScene().getWindow();
            stage.close();

    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}