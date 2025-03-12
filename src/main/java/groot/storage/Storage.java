package groot.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

import groot.tasklist.TaskList;
import groot.ui.Ui;
import groot.tasks.Task;
import groot.tasks.Todo;
import groot.tasks.Event;
import groot.tasks.Deadline;

public class Storage {
    private static final Path dataPath = Paths.get("src/main/java/groot/storage", "data.txt");

    /** 
     * Loads data from data.txt file. Method is called only after startup.
     * Parses data from data.txt file to taskList.
     * @return taskList Task List containing all task data 
     */     
    public static ArrayList<Task> loadData() {
        File dataFile = dataPath.toFile();
        ArrayList<Task> taskList = new ArrayList<>();
        if (dataFile.exists()) {
            try {
                Scanner input = new Scanner(dataFile);
                while (input.hasNextLine()) {
                    String line = input.nextLine();

                    String[] dataComponents = line.split(" \\| ");
                    String taskType = dataComponents[0];
                    Boolean isTaskCompleted = dataComponents[1].equals("1");
                    String taskDescription = dataComponents[2];
                    
                    switch (taskType) {
                    case "T":
                        Todo todoTask = Todo.createTodo(taskDescription);
                        if (todoTask != null) {
                            taskList.add(todoTask);
                        } 
                        break;
                        
                    case "D":
                        Deadline deadlineTask = Deadline.createDeadline(taskDescription);
                        if (deadlineTask != null) {
                            taskList.add(deadlineTask);
                        } 
                        break;
                        
                    case "E":
                        Event eventTask = Event.createEvent(taskDescription);
                        if (eventTask != null) {
                            taskList.add(eventTask);
                        } 
                        break;
                    default:
                        Ui.say("Something's Wrong with the data!");
                        return null;
                    }

                    if (isTaskCompleted) {
                        taskList.get(taskList.size() - 1)
                        .setDone(true);
                    }
                }
                input.close();          
            } catch (FileNotFoundException e) {
                Ui.say("Data file missing!");
                return null;
            }
        }
        return taskList;
    }

    /** 
     * Writes data to data.txt for saving.
     * Prints error if file is not found.
     */     
    public static void writeData() {
        try {
            FileWriter dataFile = new FileWriter(dataPath.toFile(), false);
            for (int i = 0; i < TaskList.getSize(); i++) {
                dataFile.write(TaskList.getTaskList().get(i).getKey() + Ui.NEW_LINE);
            }
            dataFile.close();
        } catch (IOException e) {
            Ui.say("Error writing to data file!");
        }
    }
}