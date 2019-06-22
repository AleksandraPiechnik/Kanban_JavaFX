package model;

import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import java.io.*;
import java.util.ArrayList;

public class BinFileReader extends MyFileReader {
    private FileChooser fileChooser;
    private File file;
    private FileChooser.ExtensionFilter binaryFilter = new FileChooser.ExtensionFilter("bin", "*.bin");

    @Override
    public void setFilterAndOpenFile() {

        fileChooser=new FileChooser();
        fileChooser.getExtensionFilters().removeAll();
        fileChooser.getExtensionFilters().addAll(binaryFilter);
        fileChooser.setInitialDirectory(new File("./"));
        fileChooser.setTitle("Choose file");
        file = fileChooser.showOpenDialog(null);
    }

    @Override
    public void read(KanbanDate kanbanDate, ListView toDoList, ListView inProgressList, ListView doneList) {
        clearListView(toDoList, inProgressList, doneList);
        if (file != null) {
            try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file.getName()))) {
                toDoList.getItems().addAll((ArrayList<Task>) input.readObject());
                inProgressList.getItems().addAll((ArrayList<Task>) input.readObject());
                doneList.getItems().addAll((ArrayList<Task>) input.readObject());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void clearListView(ListView toDoList, ListView inProgressList, ListView doneList)
    {
        toDoList.getItems().clear();
        inProgressList.getItems().clear();
        doneList.getItems().clear();
    }
}
