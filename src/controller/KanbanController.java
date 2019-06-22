package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import java.net.URL;
import java.util.ResourceBundle;
import model.*;
import view.ListCellUpdater;
import view.ListMenuManager;
import view.NewTaskWindow;

public class KanbanController implements Initializable {

    private KanbanDate kanbanDate=new KanbanDate();
    private NewTaskWindow taskWindow=new NewTaskWindow();
    private static final String DEFAULT_CONTROL_INNER_BACKGROUND = "derive(-fx-base,0%)";
    private ListMenuManager firstListManager;
    private ListMenuManager secondListManager;
    private ListMenuManager thirdListManager;
    private ListCellUpdater listCellUpdater=new ListCellUpdater();
    @FXML
    public javafx.scene.control.ListView firstList = new ListView(), secondList = new ListView(), thirdList = new ListView();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        firstList.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {

            @Override
            public ListCell<Task> call(ListView<Task> param) {

                final ListCell<Task> cell = new ListCell<Task>() {
                    @Override
                    protected void updateItem(Task task, boolean empty) {

                        firstListManager =new ListMenuManager("toDoList",task);
                        super.updateItem(task, empty);

                        if (task == null || empty) {
                            setText(null);
                            setStyle("-fx-control-inner-background: " + DEFAULT_CONTROL_INNER_BACKGROUND + ";");}
                        else {
                            setText(task.toString());
                            setTooltip(new Tooltip(task.getDescription()));

                            firstListManager.getDeleteItem().setOnAction(event -> firstList.getItems().remove(task));
                            firstListManager.getEditItem().setOnAction(event -> {
                                taskWindow.taskEditing(task,firstList);
                                updateItem(task, empty);
                            });
                            firstListManager.getMoveToOtherList().setOnAction(event ->{ firstListManager.moveToList(firstList,secondList,task);
                                updateItem(task, empty);});
                            firstListManager.getMoveToOtherList2().setOnAction(event ->{ firstListManager.moveToList(firstList,thirdList,task);
                                updateItem(task, empty);});

                            setContextMenu(new ContextMenu(firstListManager.getDeleteItem(), firstListManager.getEditItem(), firstListManager.getMoveToOtherList(), firstListManager.getMoveToOtherList2()));
                            listCellUpdater.updateCellColor(this,task);
                        }
                    }
                };
                return cell;
            }
        });
        secondList.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {

            @Override
            public ListCell<Task> call(ListView<Task> param) {

                final ListCell<Task> cell = new ListCell<Task>() {
                    @Override
                    protected void updateItem(Task task, boolean empty) {

                        secondListManager =new ListMenuManager("inProgressList",task);
                        super.updateItem(task, empty);

                        if (task == null || empty) {
                            setText(null);
                            setStyle("-fx-control-inner-background: " + DEFAULT_CONTROL_INNER_BACKGROUND + ";");}
                        else {
                            setText(task.toString());
                            setTooltip(new Tooltip(task.getDescription()));

                            secondListManager.getDeleteItem().setOnAction(event -> secondList.getItems().remove(task));
                            secondListManager.getEditItem().setOnAction(event -> {
                                taskWindow.taskEditing(task,secondList);
                                updateItem(task, empty);
                            });
                            secondListManager.getMoveToOtherList().setOnAction(event ->{ secondListManager.moveToList(secondList,firstList,task);
                                updateItem(task, empty);});
                            secondListManager.getMoveToOtherList2().setOnAction(event ->{ secondListManager.moveToList(secondList,thirdList,task);
                                updateItem(task, empty);});

                            setContextMenu(new ContextMenu(secondListManager.getDeleteItem(), secondListManager.getEditItem(), secondListManager.getMoveToOtherList(), secondListManager.getMoveToOtherList2()));
                            listCellUpdater.updateCellColor(this,task);
                        }
                    }
                };
                return cell;
            }
        });
        thirdList.setCellFactory(new Callback<ListView<Task>, ListCell<Task>>() {

            @Override
            public ListCell<Task> call(ListView<Task> param) {

                final ListCell<Task> cell = new ListCell<Task>() {
                    @Override
                    protected void updateItem(Task task, boolean empty) {

                        thirdListManager =new ListMenuManager("doneList",task);
                        super.updateItem(task, empty);

                        if (task == null || empty) {
                            setText(null);
                            setStyle("-fx-control-inner-background: " + DEFAULT_CONTROL_INNER_BACKGROUND + ";");}
                        else {
                            setText(task.toString());
                            setTooltip(new Tooltip(task.getDescription()));

                            thirdListManager.getDeleteItem().setOnAction(event -> thirdList.getItems().remove(task));
                            thirdListManager.getEditItem().setOnAction(event -> {
                                taskWindow.taskEditing(task,thirdList);
                                updateItem(task, empty);
                            });
                            thirdListManager.getMoveToOtherList().setOnAction(event ->{ thirdListManager.moveToList(thirdList,firstList,task);
                                updateItem(task, empty);});
                            thirdListManager.getMoveToOtherList2().setOnAction(event ->{ thirdListManager.moveToList(thirdList,secondList,task);
                                updateItem(task, empty);});

                            setContextMenu(new ContextMenu(thirdListManager.getDeleteItem(), thirdListManager.getEditItem(), thirdListManager.getMoveToOtherList(), thirdListManager.getMoveToOtherList2()));
                            listCellUpdater.updateCellColor(this,task);
                        }
                    }
                };
                return cell;
            }
        });}

    public void closeMenuItem() {
        Platform.exit();
    }
    public void saveToBinFile() {
        BinFileWriter writer= new BinFileWriter();
        writer.writeToFile(kanbanDate,firstList,secondList,thirdList);
    }
    public void openMenuItem() {
        BinFileReader reader=new BinFileReader();
        reader.readFile(kanbanDate,firstList,secondList,thirdList);
    }
    public void exportMenuItem() {
        CSVFileWriter writer= new CSVFileWriter();
        writer.writeToFile(kanbanDate,firstList,secondList,thirdList);
    }
    public void importMenuItem() {
        CSVFileReader reader=new CSVFileReader();
        reader.readFile(kanbanDate,firstList,secondList,thirdList);
    }
    public void informationMenuItem() {
        Alert alertBox = new Alert(Alert.AlertType.INFORMATION);
        alertBox.setTitle("About");
        alertBox.setHeaderText("Kanban application");
        alertBox.setContentText("made by A. Piechnik");
        alertBox.show();
    }
    public void newTaskButton() {
        taskWindow.taskAdding(firstList);
    }
}
