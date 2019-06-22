package model;

import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;

public class CSVFileWriter extends MyFileWriter {
    private File file;
    private KanbanDate kanbanDate;
    private FileChooser fileChooser;
    private FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("csv", "*.csv");

    @Override
    public void setFilterAndOpenFile() {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().removeAll();
        fileChooser.getExtensionFilters().addAll(csvFilter);
        fileChooser.setInitialDirectory(new File("./"));
        fileChooser.setTitle("Choose place to save file");
        file = fileChooser.showSaveDialog(null);
    }

    @Override
    public void write(KanbanDate kanbanDate, ListView toDoList, ListView inProgressList, ListView doneList) {

        if (file != null) {

            this.kanbanDate = kanbanDate;
            exportToCsv(toDoList, inProgressList, doneList);
        }
    }

    private void exportToCsv(ListView toDoList, ListView inProgressList, ListView doneList) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedWriter bufferedWriter = new BufferedWriter(fw);
        PrintWriter printWriter = new PrintWriter(bufferedWriter);
        convertListViewToArrayLists(toDoList,inProgressList,doneList);

        printToFile(printWriter,"TO DO LIST",kanbanDate.getToDoArrayList());
        printToFile(printWriter,"IN PROGRESS LIST",kanbanDate.getInProgressArrayList());
        printToFile(printWriter,"DONE LIST",kanbanDate.getDoneArrayList());

        printWriter.flush();
        printWriter.close();

    }

    private void printToFile(PrintWriter pw, String listName, ArrayList<Task> list) {
        pw.println(listName + "\nTitle;Priority;Exp date;Description");
        for (int i = 0; i < list.size(); i++) {
            pw.println(list.get(i).getTitle() + ";" + list.get(i).getTaskPriority() + ";" + list.get(i).getExpDate() + ";" + list.get(i).getDescription());
        }
    }

    private void convertListViewToArrayLists(ListView toDoList, ListView inProgressList, ListView doneList) {
        kanbanDate.setToDoArrayList(toDoList);
        kanbanDate.setInProgressArrayList(inProgressList);
        kanbanDate.setDoneArrayList(doneList);
    }
}
