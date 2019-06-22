package model;

import exceptions.DateFormatException;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class CSVFileReader extends MyFileReader {

    private FileChooser fileChooser;
    private File file;
    private FileChooser.ExtensionFilter csvFilter = new FileChooser.ExtensionFilter("csv", "*.csv");
    private Scanner inputStream;
    private String data="";

    @Override
    public void setFilterAndOpenFile() {
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().removeAll();
        fileChooser.getExtensionFilters().addAll(csvFilter);
        fileChooser.setInitialDirectory(new File("./"));
        fileChooser.setTitle("Choose file");
        file = fileChooser.showOpenDialog(null);
    }

    @Override
    public void read(KanbanDate kanbanDate, ListView toDoList, ListView inProgressList, ListView doneList) {
        if (file != null) {
            clearLists(toDoList, inProgressList, doneList);
            uploadLists(toDoList,inProgressList, doneList);
        }
    }

    private void uploadLists(ListView toDoList, ListView inProgressList, ListView doneList) {

        try {
            inputStream = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if(inputStream.hasNext())
        {
            data = inputStream.nextLine();
            try {
                if(data.startsWith("TO DO LIST"))
                {
                    data=inputStream.nextLine();
                    data=inputStream.nextLine();
                    uploadListView(inputStream,data,"IN PROGRESS LIST",toDoList);
                    data=inputStream.nextLine();
                    data=inputStream.nextLine();
                    uploadListView(inputStream,data,"DONE LIST",inProgressList);
                    data=inputStream.nextLine();
                    uploadListView(inputStream,data,"",doneList);
                }
                else
                {
                    System.err.println("Invalid format of file");
                    throw new DataFormatException();
                }

            }
            catch (DataFormatException e) {
                e.printStackTrace();
            }
        }
        else
        {
            System.err.println("File is empty");
        }

    }

    private void uploadListView(Scanner inputStream, String data, String listName, ListView list) {
        if(listName.equals("IN PROGRESS LIST")||listName.equals("DONE LIST")){
            while(inputStream.hasNext()&&!data.startsWith(listName))
            {
                uploadTask(list, data);
                data=inputStream.nextLine();
            }
        }
        else if(inputStream.hasNext()&&listName.startsWith(""))
        {
            if(inputStream.hasNext())
            {
                do{
                    data=inputStream.nextLine();
                    uploadTask(list, data);
                }
                while(inputStream.hasNext());
            }
        }

    }

    private void uploadTask(ListView list,String data) {
        try{
        String[] values = data.split(";");
        if(values.length!=4){
            throw new DataFormatException();
        }
        else
        {

            try{
                String dateInString = values[2];
                String []tempArr=dateInString.split("-");
                if(tempArr.length!=3) throw new DateFormatException();
                else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dateTime = LocalDate.parse(dateInString, formatter);
                    Task task = new Task(values[0], values[1], dateTime, values[3]);
                    list.getItems().add(task);

                }
            }
            catch (DateFormatException e)
            {
                    System.err.println("Invalid expDate DateFormatException occurred");
//                String dateInString = values[2];
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
//                LocalDate dateTime = LocalDate.parse(dateInString, formatter);
//                Task task = new Task(values[0], values[1],dateTime, values[3]);
//                list.getItems().add(task);

            }
        }
        }
        catch (DataFormatException e)
        {
            System.err.println("DataFormatException occurred");
        }

    }


    private void clearLists(ListView toDoList, ListView inProgressList, ListView doneList) {
        toDoList.getItems().clear();
        inProgressList.getItems().clear();
        doneList.getItems().clear();
    }
}
