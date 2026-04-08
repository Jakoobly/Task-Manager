package TaskManager.ui;

import TaskManager.exception.InvalidTitleException;
import TaskManager.model.Priority;
import TaskManager.model.Task;
import TaskManager.service.TaskManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class TaskManagerApp extends Application {
    private final TaskManager taskManager = new TaskManager();

    @Override
    public void start(Stage stage) {


        Label label = new Label("Neue Task hinzufügen!");
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        Button newButton = new Button("Hinzufügen");
        Button editButton = new Button("Bearbeiten");
        ComboBox<Priority> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(Priority.LOW, Priority.MEDIUM, Priority.HIGH);
        comboBox.setPromptText("Priorität wählen");
        ListView<Task> listView = new ListView<>();



        TextField inputField = new TextField();

        inputField.setPromptText("Titel eingeben");
        HBox inputRow = new HBox(10);
        inputRow.getChildren().addAll(comboBox, newButton, editButton);

        root.getChildren().addAll(label, inputField, inputRow, listView);
        newButton.setOnAction(e -> {
            try {
                String title = inputField.getText();
                Priority priority = comboBox.getValue();

                if (priority == null){
                    throw new IllegalArgumentException("Priorität darf nicht leer sein!");
                }

                Task task = new Task(title, priority);
                taskManager.addTask(task);

                refreshList(listView);
                inputField.clear();
                comboBox.setValue(null);

            } catch (InvalidTitleException | IllegalArgumentException ex) {
                showError(ex.getMessage());
            }
        });

        editButton.setOnAction(e -> {
            try {
                Task selectedTask = listView.getSelectionModel().getSelectedItem();

                taskManager.updateTask(selectedTask, inputField.getText(), comboBox.getValue());

                refreshList(listView);
                listView.refresh();

                inputField.clear();
                comboBox.setValue(null);
                listView.getSelectionModel().clearSelection();

            } catch (InvalidTitleException | IllegalArgumentException ex) {
                showError(ex.getMessage());
            }
        });

        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldTask, selectedTask) -> {
            if (selectedTask != null) {
                inputField.setText(selectedTask.getTitle());
                comboBox.setValue(selectedTask.getPriority());
            }
        });



        Scene scene = new Scene(root, 600, 600);

        stage.setTitle("Task Manager");
        stage.setScene(scene);
        stage.show();
    }

    private void refreshList(ListView<Task> listView) {
        listView.getItems().clear();
        listView.getItems().addAll(taskManager.getAllTasks());
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fehler");
        alert.setHeaderText("Eingabe ungültig");
        alert.setContentText(message);
        alert.showAndWait();
    }
}