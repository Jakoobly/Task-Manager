package TaskManager.ui;
import TaskManager.service.TaskManager;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        ConsoleUI console = new ConsoleUI(manager);
        console.runMenu();
    }
}