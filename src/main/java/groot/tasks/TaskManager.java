package groot.tasks;

import java.util.ArrayList;

import groot.data.Data;
import groot.ui.Ui;

public class TaskManager {

    public static void addTask(String[] commandParts, ArrayList<Task> taskList) {
        String taskType;
        String taskDescription;

        // Check for valid command. A command with < 2 Arguments is invalid 
        if (commandParts.length < 2) {
            if (commandParts[0].matches("(todo)|(deadline)|(event)")) {
                Ui.say("Missing task description!");
                return;
            } else if (commandParts[0].equals("")) {
                Ui.say("You didn't say anything!");
                return;
            } else {
                Ui.say("Sorry! I'm not quite sure what you want me to do.");
                return;
            }
            
        } else {

            taskType = commandParts[0].toLowerCase();
            taskDescription = commandParts[1];

            switch (taskType) {
            case "todo":
                Todo todoTask = Todo.createTodo(taskDescription);
                if (todoTask != null) {
                    taskList.add(todoTask);
                    break;
                } 
                return;

            case "deadline":
                Deadline deadlineTask = Deadline.createDeadline(taskDescription);
                if (deadlineTask != null) {
                    taskList.add(deadlineTask);
                    break;
                } 
                return;

            case "event":
                Event eventTask = Event.createEvent(taskDescription);
                if (eventTask != null) {
                    taskList.add(eventTask);
                    break;
                } 
                return;
        
            case "task":
                Task task = Task.createTask(taskDescription);
                if (task != null) {
                    taskList.add(task);
                    break;
                } 
                return;
                
            default:
                Ui.say("Sorry! I'm not quite sure what you want me to do.");
                return;
            }
        }

        // Last element of TaskList represents the new task
        // Get last element and say what was added to the list
        Ui.say("added: " + taskList.get(taskList.size() - 1).getDescription());
    }
}