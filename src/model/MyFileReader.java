package model;

import javafx.scene.control.ListView;

public abstract class MyFileReader {
    public void readFile(KanbanDate kanbanDate, ListView toDoList, ListView inProgressList, ListView doneList)
    {
        setFilterAndOpenFile();
        read(kanbanDate,toDoList,inProgressList,doneList);
    }
    public abstract void  setFilterAndOpenFile();
    public abstract void read(KanbanDate kanbanDate,ListView toDoList, ListView inProgressList, ListView doneList);

}
