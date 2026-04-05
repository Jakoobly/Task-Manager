package TaskManager.persistance;

import TaskManager.model.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TaskFileService {

    // sichert alle Tasks in einer Datei
    public void saveTasksToFile(List<Task> tasks ,String filename) throws IOException{
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            for(Task task : tasks){
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

    // läd Tasks aus einer Datei
    /*
    public List<Task> loadTasksFromFile(String filename) throws IOException {
        ...
    }

     */
}
