package model;

import javafx.stage.FileChooser;
import javafx.scene.control.ListView;
import view.KanbanView;

import java.io.*;
import java.util.ArrayList;


public class BinFileWriter extends MyFileWriter {
    private File file;
    private KanbanDate kanbanDate;
    private FileChooser fileChooser;
    private FileChooser.ExtensionFilter binaryFiltr = new FileChooser.ExtensionFilter("bin", "*.bin");
    //private FileChooser.ExtensionFilter csvFiltr = new FileChooser.ExtensionFilter("csv", "*.csv");


    @Override
    public void setFilterAndOpenFile() {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().removeAll(); //czy trzeba dac ktore?
        fileChooser.getExtensionFilters().addAll(binaryFiltr);
        fileChooser.setInitialDirectory(new File("./"));
        fileChooser.setTitle("Choose place to save file");
        file = fileChooser.showSaveDialog(null);
    }

    @Override
    public void write(KanbanDate kanbanDate, ListView toDoList, ListView inProgressList, ListView doneList) {

        if (file != null) {
            this.kanbanDate = kanbanDate;
            convertListViewToArrayLists(toDoList, inProgressList, doneList);
            writeLists(file.getName());

        }
    }

    private void convertListViewToArrayLists(ListView toDoList, ListView inProgressList, ListView doneList) {
        kanbanDate.setToDoArrayList(toDoList);
        kanbanDate.setInProgressArrayList(inProgressList);
        kanbanDate.setDoneArrayList(doneList);
    }

    private void writeLists(String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(kanbanDate.getToDoArrayList());
            outputStream.writeObject(kanbanDate.getInProgressArrayList());
            outputStream.writeObject(kanbanDate.getDoneArrayList());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            kanbanDate.getToDoArrayList().clear();
            kanbanDate.getInProgressArrayList().clear();
            kanbanDate.getDoneArrayList().clear();
        }
    }
}
