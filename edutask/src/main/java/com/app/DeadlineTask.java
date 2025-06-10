package com.app;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

//Ini adalah pilar PEWARISAN (Inheritance)
public class DeadlineTask extends Task {

    // Properti tambahan, hanya ada di kelas ini
    private final ObjectProperty<LocalDate> deadline;

    public DeadlineTask(String description, String priority, LocalDate deadline) {
        // Memanggil constructor dari kelas induk (Task) untuk menginisialisasi
        // properti description dan priority.
        super(description, priority);
        
        // Menginisialisasi properti spesifik kelas ini
        this.deadline = new SimpleObjectProperty<>(deadline);
    }

    // Metode khusus untuk mengakses properti deadline
    public ObjectProperty<LocalDate> deadlineProperty() {
        return deadline;
    }

    public LocalDate getDeadline() {
        return deadline.get();
    }
}