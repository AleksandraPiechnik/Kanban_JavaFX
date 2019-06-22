package view;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.Task;

import java.time.LocalDate;

public class NewTaskWindow {

    Dialog<Task> dialog;
    DialogPane dialogPane;
    TextField titleField;
    ChoiceBox priorityField;
    DatePicker dateField = new DatePicker(LocalDate.now());
    TextArea descriptionArea;

    public NewTaskWindow() {

        dialog = new Dialog<>();
        dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        priorityField = new ChoiceBox();
        priorityField.getItems().addAll("Low", "Medium", "High");
        priorityField.getSelectionModel().selectFirst();
        dateField.setEditable(false);

    }

    public void taskAdding(ListView list) {
        dialog.setTitle("New task");
        dialog.setHeaderText("Fill each gap in order to create task");
        titleField = new TextField("Title");
        descriptionArea = new TextArea("Description");
        dialogPane.setContent(new VBox(8, titleField, priorityField, dateField, descriptionArea));
        Platform.runLater(titleField::requestFocus);


        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                list.getItems().add(new Task(titleField.getText(), (String) priorityField.getValue(), dateField.getValue(), descriptionArea.getText()));
            }
            return null;
        });
        dialog.showAndWait();
    }
    public void taskEditing(Task task, ListView list)
    {

        dialog.setTitle("Edit task");
        dialog.setHeaderText("Edit gaps");
        titleField = new TextField(task.getTitle());
        priorityField.getSelectionModel().select(task.getTaskPriority());
        dateField = new DatePicker(task.getExpDate());
        descriptionArea = new TextArea(task.getDescription());
        dialogPane.setContent(new VBox(8, titleField, priorityField, dateField, descriptionArea));
        Platform.runLater(titleField::requestFocus);

        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
                task.setTitle(titleField.getText());
                task.setTaskPriority((String) priorityField.getValue());
                task.setExpDate(dateField.getValue());
                task.setDescription(descriptionArea.getText());
            }
            list.getSelectionModel().clearSelection(0);
            return null;
        });
        dialog.showAndWait();
    }

}
