package com.app;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class SimpleTask extends Task {

    // Constructor
    public SimpleTask(String description, String priority) {
        super(description, priority);
    }

    @Override
    public ObjectProperty<LocalDate> deadlineProperty() {
        return new SimpleObjectProperty<>(null);
    }
}