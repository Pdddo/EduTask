package com.app;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Task {

    private final StringProperty description;
    private final StringProperty priority;
    private final BooleanProperty done;
    public abstract ObjectProperty<LocalDate> deadlineProperty();

    public Task(String description, String priority) {
        this.description = new SimpleStringProperty(description);
        this.priority = new SimpleStringProperty(priority);
        this.done = new SimpleBooleanProperty(false);
    }

    // Methods getter dan setter
    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty priorityProperty() {
        return priority;
    }

    public BooleanProperty doneProperty() {
        return done;
    }

    public String getPriority() {
        return priority.get();
    }
    
}