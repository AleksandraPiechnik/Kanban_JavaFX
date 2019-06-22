package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
    String title;
    String taskPriority;
    LocalDate expDate;
    String Description;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public String getTitle() {
        return title;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public String getDescription() {
        return Description;
    }

    public Task(){}
    public Task(String title, String taskPriority, LocalDate expDate, String description) {
        this.title = title;
        this.taskPriority = taskPriority;
        this.expDate = expDate;
        Description = description;
    }

    @Override
    public String toString() {
        return title;
    }
}
