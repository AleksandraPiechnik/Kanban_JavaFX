package model;

import javafx.scene.control.ListView;

import java.util.ArrayList;

public class KanbanDate {
    private ArrayList<Task> toDoArrayList = new ArrayList<>();
    private ArrayList<Task> inProgressArrayList = new ArrayList<>();
    private ArrayList<Task> doneArrayList = new ArrayList<>();

    public ArrayList<Task> getToDoArrayList() {
        return toDoArrayList;
    }

    public ArrayList<Task> getInProgressArrayList() {
        return inProgressArrayList;
    }

    public ArrayList<Task> getDoneArrayList() {
        return doneArrayList;
    }

    public void setToDoArrayList(ListView list) {
        toDoArrayList.addAll((list.getItems()));
    }

    public void setInProgressArrayList(ListView list) {
        inProgressArrayList.addAll(list.getItems());
    }

    public void setDoneArrayList(ListView list) {
        doneArrayList.addAll(list.getItems());
    }
}
