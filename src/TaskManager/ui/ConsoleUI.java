package TaskManager.ui;
import TaskManager.exception.InvalidIndexException;
import TaskManager.exception.InvalidTitleException;
import TaskManager.model.Priority;
import TaskManager.model.Task;
import TaskManager.service.TaskManager;

import java.util.Scanner;

public class ConsoleUI {
    private final TaskManager taskManager;

    public ConsoleUI(TaskManager taskManager){
        this.taskManager = taskManager;
    }

    private final Scanner scanner = new Scanner(System.in);

    private void waitForEnter(){
        System.out.println();
        System.out.println("Weiter mit Enter...");
        scanner.nextLine();
    }

    private void noTasksAndOutput(){
        System.out.println("Keine Tasks vorhanden");
        waitForEnter();
    }

    private int readInt(){
        while (!scanner.hasNextInt()) {
            System.out.println("Bitte eine Zahl eingeben.");
            scanner.nextLine();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public void runMenu(){
        while (true){
            System.out.println("Willkommen im Menü");
            System.out.println("Welche Aktion möchten Sie durchführen");
            System.out.println("------------------------------");
            System.out.println("1 - neue Task anlegen");
            System.out.println("2 - vorhandene Task bearbeiten");
            System.out.println("3 - vorhandene Task löschen");
            System.out.println("4 - alle Tasks ausgeben");
            System.out.println("5 - nur offene Tasks ausgeben");
            System.out.println("6 - Menü verlassen");

            int input = readInt();

            if(input == 1){
                addTask();
            }else if(input == 2){
                if(taskManager.isEmpty()){
                    noTasksAndOutput();
                }else{
                    editTask();
                }
            }else if(input == 3) {
                if(taskManager.isEmpty()){
                    noTasksAndOutput();
                }else{
                    deleteTask();
                }
            }else if(input == 4){
                if(taskManager.isEmpty()){
                    noTasksAndOutput();
                }else{
                    System.out.println("Tasks:");
                    taskManager.printAllTasks();
                    waitForEnter();
                }
            }else if(input == 5){
                if(taskManager.isEmpty()){
                    noTasksAndOutput();
                }else{
                    System.out.println("Tasks:");
                    taskManager.printUnfinishedTasks();
                    waitForEnter();
                }
            }else if(input == 6){
                System.out.println("Programm beendet");
                return;
            }else{
                System.out.println("Fehlerhafte Eingabe");
                waitForEnter();
            }
        }
    }

    private void addTask(){
        System.out.println("Titel: ");
        String title = scanner.nextLine();

        try{
            System.out.println("Priorität (LOW, MEDIUM, HIGH): ");
            String input = scanner.nextLine();
            Priority priority = Priority.valueOf(input.toUpperCase());
            Task task = new Task(title, priority);
            taskManager.addTask(task);

            System.out.println("Task erstellt!");
        }catch(IllegalArgumentException e){
            System.out.println("Fehler: " + e.getMessage());
        }
        waitForEnter();
    }

    private void editTask(){
        System.out.println("Tasks:");
        taskManager.printAllTasks();
        System.out.println("Bitte den Index der Task eingeben");
        int index = readInt();
        System.out.println("Was möchten Sie verändern?");
        System.out.println("------------------------------");
        System.out.println("1 - Task abhaken");
        System.out.println("2 - Titel ändern");

        int input = readInt();

        if(input == 1){
            try{
                taskManager.toggle(index);
            }catch(InvalidIndexException e){
                System.out.println("Fehler: " + e.getMessage());
            }finally {
                waitForEnter();
            }
        }else if(input == 2){
            System.out.println("Neuen Titel eingeben: ");
            String title = scanner.nextLine();
            try{
                taskManager.changeTitle(index, title);
            }catch(InvalidTitleException e){
                System.out.println("Fehler: " + e.getMessage());
            }catch(InvalidIndexException e){
                System.out.println("Fehler: " + e.getMessage());
            }finally {
                waitForEnter();
            }
        }
        else{
            System.out.println("Fehlerhafte Eingabe");
            waitForEnter();
        }
    }

    private void deleteTask(){
        System.out.println("Tasks:");
        taskManager.printAllTasks();
        System.out.println("Bitte Index der Task eingeben");
        int index = readInt();

        try{
            taskManager.deleteTask(index);
        }catch(IllegalArgumentException e){
            System.out.println("Fehler: " + e.getMessage());
        }finally {
            waitForEnter();
        }
    }
}