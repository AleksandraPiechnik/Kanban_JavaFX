package view;

import javafx.scene.control.ListCell;
import model.Task;


public class ListCellUpdater {
    private static final String LOW_CONTROL_INNER_BACKGROUND = "derive(palegreen,0%)";
    private static final String MEDIUM_CONTROL_INNER_BACKGROUND = "derive(yellow, 0%)";
    private static final String HIGH_CONTROL_INNER_BACKGROUND = "derive(red, 0%)";


    public void updateCellColor(ListCell<Task> cell, Task task) {

        if (task.getTaskPriority().equals("Low")) {
            cell.setStyle("-fx-control-inner-background: " + LOW_CONTROL_INNER_BACKGROUND + ";");
        } else if (task.getTaskPriority().equals("Medium")) {
            cell.setStyle("-fx-control-inner-background: " + MEDIUM_CONTROL_INNER_BACKGROUND + ";");
        } else if (task.getTaskPriority().equals("High")) {
            cell.setStyle("-fx-control-inner-background: " + HIGH_CONTROL_INNER_BACKGROUND + ";");
        }

    }

}
