package model;
import javafx.scene.control.ListView;




public abstract class MyFileWriter {


    public void writeToFile(KanbanDate kanbanDate, ListView toDoList, ListView inProgressList, ListView doneList)
    {
       setFilterAndOpenFile();
       write(kanbanDate,toDoList,inProgressList,doneList);
    }
    public abstract void  setFilterAndOpenFile();
    public abstract void write(KanbanDate kanbanDate,ListView toDoList, ListView inProgressList, ListView doneList);


}
