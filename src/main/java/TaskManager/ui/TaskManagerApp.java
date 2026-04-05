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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.awt.*;

public class TaskManagerApp extends Application {
    private final TaskManager taskManager = new TaskManager();

    @Override
    public void start(Stage stage) {


        Label label = new Label("Neue Task hinzufügen!");
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        Button button = new Button("Hinzufügen");
        ListView<String> listView = new ListView<>();

        TextField inputField = new TextField();

        inputField.setPromptText("Titel eingeben");
        root.getChildren().addAll(label, inputField, button, listView);
        button.setOnAction(e -> {
            try {
                String title = inputField.getText();

                Task task = new Task(title, Priority.MEDIUM);
                taskManager.addTask(task);

                refreshList(listView);
                inputField.clear();

            } catch (InvalidTitleException | IllegalArgumentException ex) {
                showError(ex.getMessage());
            }
        });


        Scene scene = new Scene(root, 600, 600);

        stage.setTitle("Task Manager");
        stage.setScene(scene);
        stage.show();
    }

    private void refreshList(ListView<String> listView) {
        listView.getItems().clear();

        for (Task task : taskManager.getAllTasks()) {
            String text = task.getTitle() + " | " + task.getPriority();
            listView.getItems().add(text);
        }
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