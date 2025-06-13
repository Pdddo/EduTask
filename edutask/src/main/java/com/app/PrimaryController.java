package com.app;

import java.io.IOException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrimaryController {

    // Deklarasi ObservableList
    private static ObservableList<Task> highPriorityTasks = FXCollections.observableArrayList();
    private static ObservableList<Task> mediumPriorityTasks = FXCollections.observableArrayList();
    private static ObservableList<Task> lowPriorityTasks = FXCollections.observableArrayList();

    // Deklarasi Tabel
    @FXML private TableView<Task> highPriorityTable;
    @FXML private TableView<Task> mediumPriorityTable;
    @FXML private TableView<Task> lowPriorityTable;

    // High Priority Columns
    @FXML private TableColumn<Task, Boolean> highDoneCol;
    @FXML private TableColumn<Task, String> highDescCol;
    @FXML private TableColumn<Task, LocalDate> highDeadlineCol;
    
    // Medium Priority Columns
    @FXML private TableColumn<Task, Boolean> mediumDoneCol;
    @FXML private TableColumn<Task, String> mediumDescCol;
    @FXML private TableColumn<Task, LocalDate> mediumDeadlineCol;
    
    // Low Priority Columns
    @FXML private TableColumn<Task, Boolean> lowDoneCol;
    @FXML private TableColumn<Task, String> lowDescCol;
    @FXML private TableColumn<Task, LocalDate> lowDeadlineCol;

    // Button Untuk Menambah Task (Berpindah Scene)
    @FXML private Button addTaskButton;

    // Button untuk Menghapus Task
    @FXML private Button deleteTaskButton;

    
    @FXML
    public void initialize() {
        highPriorityTable.setItems(highPriorityTasks);
        mediumPriorityTable.setItems(mediumPriorityTasks);
        lowPriorityTable.setItems(lowPriorityTasks);
        
        // Setup High Priority Table
        highDoneCol.setCellValueFactory(new PropertyValueFactory<>("done"));
        highDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        highDeadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));

        // Setup Medium Priority Table
        mediumDoneCol.setCellValueFactory(new PropertyValueFactory<>("done"));
        mediumDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        mediumDeadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));

        // Setup Low Priority Table
        lowDoneCol.setCellValueFactory(new PropertyValueFactory<>("done"));
        lowDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        lowDeadlineCol.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        
        setupEditableCheckBox(highDoneCol);
        setupEditableCheckBox(mediumDoneCol);
        setupEditableCheckBox(lowDoneCol);
        
        highPriorityTable.setEditable(true);
        mediumPriorityTable.setEditable(true);
        lowPriorityTable.setEditable(true);
    }
    
    // Setup untuk checkbox
    private void setupEditableCheckBox(TableColumn<Task, Boolean> column) {
        column.setCellFactory(CheckBoxTableCell.forTableColumn(column));
        column.setEditable(true);
    }

    // Fungsi untuk Menambah Task
    public static void addTask(Task task) {
        if (task == null) return;

        switch (task.getPriority()) {
            case "High": highPriorityTasks.add(task);
                break;
            case "Medium": mediumPriorityTasks.add(task);
                break;
            case "Low": lowPriorityTasks.add(task);
                break;
        }
    }
    
    // Fungsi untuk Menghapus Task
    @FXML
    private void deleteSelectedTask() {
        if (tryDeleteFrom(highPriorityTable, highPriorityTasks)) return;
        if (tryDeleteFrom(mediumPriorityTable, mediumPriorityTasks)) return;
        if (tryDeleteFrom(lowPriorityTable, lowPriorityTasks)) return;
    }
    private boolean tryDeleteFrom(TableView<Task> table, ObservableList<Task> list) {
        Task selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            list.remove(selected);
            return true;
        }
        return false;
    }

    @FXML
      private void switchToSecondary() throws IOException {
        // Buat jendela baru
        Stage popupStage = new Stage();
        popupStage.setTitle("Add New Task");

        // Muat FXML untuk jendela baru
        Parent root = FXMLLoader.load(App.class.getResource("secondary.fxml"));

        // Buat Scene Baru
        Scene popupScene = new Scene(root);

        // Pasang Scene ke jendela baru
        popupStage.setScene(popupScene);

        // Atur agar jendela utama tidak bisa di-klik
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(addTaskButton.getScene().getWindow());

        // Atur agar jendela pop-up tidak bisa di-resize
        popupStage.setResizable(false);

        // Menampilkan jendela pop-up dan tunggu sampai pengguna selesai
        popupStage.showAndWait();
    }
}