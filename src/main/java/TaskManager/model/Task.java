package TaskManager.model;

import TaskManager.exception.InvalidCategoryException;
import TaskManager.exception.InvalidPriorityException;
import TaskManager.exception.InvalidTitleException;

import java.time.LocalDateTime;

public class Task {
    private String title;
    private boolean done;
    private Priority priority;
    private Category category;
    private static final int MAX_TITLE_LENGTH = 100;
    private LocalDateTime createdAt;

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
        return done;
    }

    public String getStatusIcon(){
        return done ? "[✓]" : "[ ]";
    }

    public void toggleDone(){
        done = !done;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    @Override
    public String toString(){
        return getStatusIcon() + " " + getTitle() + ", Priority: " + getPriority() + ", Category: " + getCategory();
    }
}
