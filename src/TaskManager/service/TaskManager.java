package TaskManager.service;

import TaskManager.exception.InvalidIndexException;
import TaskManager.exception.InvalidTitleException;
import TaskManager.model.Priority;
import TaskManager.model.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private List<Task> tasksList = new ArrayList<>();

    // fügt eine Tast hinzu
    public void addTask(Task task){
        tasksList.add(task);
    }

    public boolean isEmpty(){
        return tasksList.isEmpty();
    }

    // gibt die Task basierend auf dem Index aus (1 basiert)
    public Task getTask(int index){
        if(index < 1 || index > tasksList.size()){
            throw new InvalidIndexException("Ungültiger Index!");
        }
        return tasksList.get(index - 1);
    }

    // löscht eine Task basierend auf dem Index
    public void deleteTask(int index) throws IllegalArgumentException{
        Task task = getTask(index);
        tasksList.remove(task);
    }

    // ändert den Zustand (erledigt / noch offen)
    public void toggle(int index) throws InvalidIndexException{
        getTask(index).toggleDone();
    }

    public void changeTitle(int index, String title) throws InvalidIndexException, InvalidTitleException {
        getTask(index).setTitle(title);
    }

    // druckt alle vorhandenen Tasks aus
    public void printAllTasks(){
        if(tasksList.isEmpty()){
            System.out.println("Die Liste ist leer");
        }else{
            for(int i= 0; i < tasksList.size(); i++){
                System.out.println((i + 1) + " - " + tasksList.get(i));
            }
        }
    }

    // druckt nur die noch vorhandenen Tasks aus
    public void printUnfinishedTasks(){
        for(Task task : tasksList){
            if(!task.isDone()){
                System.out.println(task);
            }
        }
    }

    // druckt nur Tasks mit hoher Priorität aus
    public void printHighPriority(){
        for(Task task : tasksList){
            if(task.getPriority() == Priority.HIGH){
                System.out.println(task);
            }
        }
    }

    // gibt eine neue Liste mit allen noch offenen Tasks zurück
    public List<Task> getUnfinishedTasks(){
        return tasksList.stream()
                .filter(task -> !task.isDone())
                .collect(Collectors.toList());
    }

    // gibt eine neue Liste mit sortierten Tasks zurück (Low -> High & dann nach Titel)
    public List<Task> getTasksSortedByPriority(){
        return tasksList.stream()
                .sorted(Comparator.comparing(Task::getPriority).reversed()
                    .thenComparing(Task::getTitle)
                )
                .collect(Collectors.toList());
    }

    // zählt noch offene Tasks
    public long countUnfinishedTasks(){
        return tasksList.stream()
                .filter(task -> !task.isDone())
                .count();
    }

    // sichert alle Tasks in einer Datei
    public void saveTasksToFile(String filename){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            for(Task task : tasksList){
                String line = task.getTitle() + ","
                        + task.isDone() + ","
                        + task.getPriority();
                writer.write(line);
                writer.newLine();
            }
        }catch(IOException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }
}