package view;

import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import model.Task;

public class ListMenuManager {
    private MenuItem deleteItem;
    private MenuItem editItem;
    private MenuItem moveToOtherList1;
    private MenuItem moveToOtherList2;
    private String listName;
    private ListView<Task> list;
    Task task;

    public MenuItem getDeleteItem() {
        return deleteItem;
    }

    public MenuItem getEditItem() {
        return editItem;
    }

    public MenuItem getMoveToOtherList() {
        return moveToOtherList1;
    }

    public MenuItem getMoveToOtherList2() {
        return moveToOtherList2;
    }
    public ListMenuManager(ListView<Task>list,String listName) {
        deleteItem = new MenuItem("Delete");
        editItem = new MenuItem("Edit");
        this.listName=listName;
        this.list=list;

        if(listName.equals("toDoList"))
        {
            moveToOtherList1 = new MenuItem("Move to IN PROGRESS list");
            moveToOtherList2 = new MenuItem("Move to DONE list");
        }
        if(listName.equals("inProgressList"))
        {
            moveToOtherList1 = new MenuItem("Move to TO DO list");
            moveToOtherList2 = new MenuItem("Move to DONE list");
        }
        if(listName.equals("doneList"))
        {
            moveToOtherList1 = new MenuItem("Move to TO DO list");
            moveToOtherList2 = new MenuItem("Move to IN PROGRESS list");
        }
    }

    public ListMenuManager(String listName, Task task) {
        deleteItem = new MenuItem("Delete");
        editItem = new MenuItem("Edit");
        this.task=task;
        this.listName=listName;

        if(listName.equals("toDoList"))
        {
            moveToOtherList1 = new MenuItem("Move to IN PROGRESS list");
            moveToOtherList2 = new MenuItem("Move to DONE list");
        }
        if(listName.equals("inProgressList"))
        {
            moveToOtherList1 = new MenuItem("Move to TO DO list");
            moveToOtherList2 = new MenuItem("Move to DONE list");
        }
        if(listName.equals("doneList"))
        {
            moveToOtherList1 = new MenuItem("Move to TO DO list");
            moveToOtherList2 = new MenuItem("Move to IN PROGRESS list");
        }

    }
    public void moveToList(ListView list, ListView list2, Task task)
    {
        list2.getItems().add(task);
        list.getItems().remove(task);
    }

}
