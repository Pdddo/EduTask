package com.app;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class DeadlineTask extends Task {

    // Properti Khusus class ini
    private final ObjectProperty<LocalDate> deadline;

    // Constructor
    public DeadlineTask(String description, String priority, LocalDate deadline) {
        super(description, priority);
        this.deadline = new SimpleObjectProperty<>(deadline);
    }

    // Getter dan Setter
    public LocalDate getDeadline() {
        return deadline.get();
    }

    @Override
    public ObjectProperty<LocalDate> deadlineProperty() {
        return deadline;
    }
}