package TaskManager.ui;
import TaskManager.persistance.TaskFileService;
import TaskManager.service.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        TaskFileService fileService = new TaskFileService();
        ConsoleUI console = new ConsoleUI(manager, fileService);

        console.runMenu();
    }
}