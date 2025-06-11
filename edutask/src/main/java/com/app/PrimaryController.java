package com.app;

import java.io.IOException;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController {

    // Daftar statis untuk menyimpan tugas agar data tidak hilang saat berpindah scene
    private static ObservableList<Task> highPriorityTasks = FXCollections.observableArrayList();
    private static ObservableList<Task> mediumPriorityTasks = FXCollections.observableArrayList();
    private static ObservableList<Task> lowPriorityTasks = FXCollections.observableArrayList();

    // --- FXML TABLE REFERENCES ---
    @FXML private TableView<Task> highPriorityTable;
    @FXML private TableView<Task> mediumPriorityTable;
    @FXML private TableView<Task> lowPriorityTable;

    // --- FXML COLUMN REFERENCES ---
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


    @FXML
    public void initialize() {
        highPriorityTable.setItems(highPriorityTasks);
        mediumPriorityTable.setItems(mediumPriorityTasks);
        lowPriorityTable.setItems(lowPriorityTasks);

        // Mengatur setiap kolom untuk tahu data mana yang harus ditampilkan dari objek Task
        // Argumen "description", "deadline", "done" harus SAMA PERSIS dengan nama properti di Task.java
        
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
    
    private void setupEditableCheckBox(TableColumn<Task, Boolean> column) {
        column.setCellFactory(CheckBoxTableCell.forTableColumn(column));
        column.setEditable(true);
    }

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
    
    @FXML
    private void deleteSelectedTask() {
        // Mencoba menghapus dari setiap tabel. Hanya satu yang akan berhasil.
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
        App.setRoot("secondary");
    }
}