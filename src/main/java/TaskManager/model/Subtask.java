package TaskManager.model;

import TaskManager.exception.InvalidTitleException;

public class Subtask {
    private String title;
    private boolean done;
    private static final int MAX_TITLE_LENGTH = 100;

    public Subtask(String title){
        validateTitle(title);
        this.title = title;
        this.done = false;
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
        this.title = title;
    }

    public boolean isDone(){
        return done;
    }

    public void setDone(boolean done){
        this.done = done;
    }

    public void toggleDone(){
        done = !done;
    }

    @Override
    public String toString(){
        return "Title: " + getTitle() + ", done: " + isDone();
    }
}
