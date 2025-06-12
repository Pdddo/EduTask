package com.app;

import java.io.IOException;
import java.time.LocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

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

        // Atur batas minimum tanggal pada DatePicker
        deadlinePicker.setDayCellFactory(getDateCellFactory());
        deadlinePicker.setValue(LocalDate.now());

        // Menyembunyikan deadline saat checkbox tidak dicentang
        hasDeadlineCheckBox.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
            deadlineContainer.setVisible(isNowSelected);
            deadlineContainer.setManaged(isNowSelected);
        });
    }

    // Menyimpan task baru
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

    // Reset input
    private void resetFields() {
        descriptionField.clear(); // reset deskripsi
        priorityChoiceBox.setValue("Medium"); // reset prioritas
        deadlinePicker.setValue(LocalDate.now()); // reset deadline
        hasDeadlineCheckBox.setSelected(false);// reset checkbok
    }

    // Menutup jendela
    @FXML
    private void closeWindow() throws IOException {
        Stage stage = (Stage) closeWindow.getScene().getWindow();
        stage.close();
    }

    // Menampilkan error
    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Membatasi pemilihan tanggal ke hari ini dan sesudahnya
    private Callback<DatePicker, DateCell> getDateCellFactory() {
        return picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (item.isBefore(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #dddddd;");
                }
            }
        };
    }
}
