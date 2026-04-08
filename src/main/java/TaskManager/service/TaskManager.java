package TaskManager.service;

import TaskManager.exception.InvalidIndexException;
import TaskManager.exception.InvalidTitleException;
import TaskManager.model.Priority;
import TaskManager.model.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private final List<Task> tasksList = new ArrayList<>();

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

    // deleteTask für GUI
    public void deleteTask(Task task){
        tasksList.remove(task);
    }

    // updateTask für GUI
    public void updateTask(Task task, String newTitle, Priority newPriority){
        if(task == null){
            throw new IllegalArgumentException("Bitte eine Task auswählen");
        }
        task.setTitle(newTitle);
        task.setPriority(newPriority);
    }

    // ändert den Zustand (erledigt / noch offen)
    public void toggle(int index) throws InvalidIndexException{
        getTask(index).toggleDone();
    }

    public void changeTitle(int index, String title) throws InvalidIndexException, InvalidTitleException {
        getTask(index).setTitle(title);
    }

    // alle Tasks als neue Liste zurückgeben
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasksList);
    }

    // Task verschieben (von altem Index zu neuem Index)
    public void moveTask(int fromIndex, int toIndex){
        if(fromIndex < 1 || fromIndex > tasksList.size()
        || toIndex < 1 || toIndex > tasksList.size()){
            throw new InvalidIndexException("Ungültiger Index");
        }
        Task task = tasksList.remove(fromIndex -1);
        tasksList.add(toIndex -1, task);
    }

    // gibt nur Tasks mit hoher Priorität zurück
    public List<Task> getHighPriorityTasks(){
        return tasksList.stream()
                .filter(task -> task.getPriority() == Priority.HIGH)
                .collect(Collectors.toList());
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
}