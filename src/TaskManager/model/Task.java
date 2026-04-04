package TaskManager.model;

import TaskManager.exception.InvalidTitleException;

public class Task {
    private String title;
    private boolean done;
    private Priority priority;

    public Task(String title){
        this(title, Priority.LOW);
    }

    public Task(String title, Priority priority){
        if(title == null || title.isBlank()){
            throw new IllegalArgumentException("Fehler bei der Vergabe des Titels");
        }
        if(priority == null){
            throw new IllegalArgumentException("Fehler bei der Vergabe der Priority");
        }
        this.title = title;
        this.priority = priority;
        this.done = false;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        if(title == null || title.isBlank()){
            throw new InvalidTitleException("Fehler bei der Vergabe des Titels");
        }
        this.title = title;
    }

    public boolean isDone(){
        return done;
    }

    public Priority getPriority(){
        return priority;
    }

    public String getStatusIcon(){
        return done ? "[✓]" : "[ ]";
    }

    public void toggleDone(){
        done = !done;
    }

    @Override
    public String toString(){
        return getStatusIcon() + " " + getTitle() + ", Priority: " + getPriority();
    }
}
