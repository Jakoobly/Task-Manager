package TaskManager.model;

import TaskManager.exception.InvalidCategoryException;
import TaskManager.exception.InvalidIndexException;
import TaskManager.exception.InvalidPriorityException;
import TaskManager.exception.InvalidTitleException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Todo: Logik überlegen wie mit Fortschritt von Task / Subtask umgegangen wird -> Kombinieren?
// Todo: CLI Ausgabe Subtasks integrieren

public class Task {
    private String title;
    private boolean done;
    private Priority priority;
    private Category category;
    private static final int MAX_TITLE_LENGTH = 100;
    private LocalDateTime createdAt;

    private final List<Subtask> subtasks = new ArrayList<>();


    public Task(String title){
        this(title, Priority.LOW);
    }

    public Task(String title, Priority priority){
        this(title, priority, new Category("Default"));
    }

    public Task(String title, Priority priority, Category category){
        validateTitle(title);
        if(priority == null){
            throw new InvalidPriorityException("Fehler bei der Vergabe der Priority");
        }
        if (category == null){
            throw new InvalidCategoryException("Kategorie dard nicht leer sein");
        }
        this.title = title.trim();
        this.priority = priority;
        this.category = category;
        this.done = false;
        this.createdAt = LocalDateTime.now();
    }

    private void validateTitle(String title){
        if(title == null || title.isBlank()){
            throw new InvalidTitleException("Fehler bei der Vergabe des Titels");
        }
        if(title.length() > MAX_TITLE_LENGTH){
            throw new InvalidTitleException("Der Titel ist zu lang");
        }
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        validateTitle(title);
        this.title = title.trim();
    }

    public void setPriority(Priority priority){
        if(priority == null){
            throw new InvalidPriorityException("Priorität darf nicht leer sein!");
        }
        this.priority = priority;
    }

    public Priority getPriority(){
        return priority;
    }

    public void setCategory(Category category){
        if (category == null){
            throw new InvalidCategoryException("Kategorie dard nicht leer sein");
        }
        this.category = category;
    }

    public Category getCategory(){
        return category;
    }


    public boolean isDone(){
        if (subtasks.isEmpty()){
            return done;
        }
        return subtasks.stream().allMatch(Subtask::isDone);
    }

    public String getStatusIcon(){
        return isDone() ? "[✓]" : "[ ]";
    }

    public void toggleDone(){
        if(subtasks.isEmpty()){
            done = !done;
        } else {
            boolean allDone = subtasks.stream().allMatch(Subtask::isDone);
            for (Subtask subtask: subtasks){
                subtask.setDone(!allDone);
            }
        }

    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }



    // fügt eine Subtask einer Task hinzu
    public void addSubtask(Subtask subtask){
        if(subtask == null){
            throw new IllegalArgumentException("Subtask darf nicht null sein");
        }
        subtasks.add(subtask);
    }

    // löscht eine Subtask einer Task (CLI)
    public void removeSubtask(int index){
        if(subtasks.isEmpty() || index < 1 || index > subtasks.size()){
            throw new InvalidIndexException("Dieser Index existiert nicht");
        }
        subtasks.remove(index - 1);
    }

    // löscht eine Subtask einer Task (GUI)
    public void removeSubtask(Subtask subtask){
        if(subtask == null || !subtasks.contains(subtask)){
            throw new IllegalArgumentException("Subtask nicht vorhanden");
        }
        subtasks.remove(subtask);
    }

    // gibt eine NEUE subtask-Liste zurück
    public List<Subtask> getSubtasks(){
        return new ArrayList<>(subtasks);
    }

    public long countFinishedSubtasks(){
        return subtasks.stream()
                .filter(Subtask::isDone)
                .count();
    }

    public double getProgressInPercent(){
        if(subtasks.isEmpty()){
            return 0.0;
        }
        return ((double) countFinishedSubtasks() / subtasks.size()) * 100;
    }

    public Subtask getSubtask(int index){
        if(index < 1 || index > subtasks.size()){
            throw new InvalidIndexException("Ungültiger Index!");
        }
        return subtasks.get(index - 1);
    }

    public void changeTitle(int index, String title) throws InvalidIndexException, InvalidTitleException {
        getSubtask(index).setTitle(title);
    }

    public void updateSubtask(Subtask subtask, String newTitle, boolean newDone){
        if(subtask == null){
            throw new IllegalArgumentException("Bitte eine Subtask auswählen");
        }
        subtask.setTitle(newTitle);
        subtask.setDone(newDone);
    }

    public String getFormattedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return createdAt.format(formatter);
    }

    @Override
    public String toString(){
        return getStatusIcon() + " " + getTitle() + ", Priority: " + getPriority() + ", Category: " + getCategory() + ", Time: " + getFormattedCreatedAt();
    }
}
