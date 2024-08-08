package Task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TaskManager {
    private static final String JSON_File_Path = "tasks.json";
    private static List<Task> taskList = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static void loadTasksFromFile(){
        try{
            File file = new File(JSON_File_Path);
            if (file.exists()){
                Task[] taskArray = objectMapper.readValue(file, Task[].class);
                taskList = new ArrayList<>(List.of(taskArray));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void saveTasksToFile(){
        try{
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(JSON_File_Path), taskList.toArray(new Task[0]));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static int getNextTaskId(){
        if (taskList.isEmpty()){
            return 1; //start from 1 if list is empty
        }
        return taskList.stream()
                .mapToInt(Task::getTaskId)
                .max()
                .getAsInt() + 1;
    }

    private static Task findTaskById(int id){
        return taskList.stream()
                .filter(task -> task.getTaskId() == id)
                .findFirst()
                .orElse(null);
    }

    private static void markTaskCompleted(){
        System.out.println("Enter TaskID to mark Task as completed: ");
        int id = sc.nextInt();
        Task task = findTaskById(id);
        if (task == null){
            System.out.println("Task Not Found!");
            return;
        }

        task.setCompleted(true);
        saveTasksToFile();

    }

    private static void deleteTask(){
        System.out.println("Enter TaskId to Delete Task: ");
        int id = sc.nextInt();
        Task task = findTaskById(id);
        if (task == null){
            System.out.println("Task Not Found!");
            return;
        }
        taskList.remove(task);
        saveTasksToFile();

    }

    private static void updateTask(){
        System.out.println("Enter TaskId to Update task: ");
        int id = sc.nextInt();
        sc.nextLine();
        Task task = findTaskById(id);
        if (task == null){
            System.out.println("Task Not Found!");
            return;
        }
        System.out.println("Enter New Description: ");
        String description = sc.nextLine();
        System.out.println("Enter New Due Date(yyyy-mm-dd)");
        String due_Date = sc.nextLine();

        task.setDescription(description);
        task.setDueDate(due_Date);
        saveTasksToFile();
    }

    private static void viewAllTasks(){
        if(taskList.isEmpty()){
            System.out.println("No Tasks Found!");
            return;
        }
        taskList.forEach(System.out::println);
    }

    private static void addTask(){
        System.out.print("Enter task description: ");
        String description = sc.nextLine();
        System.out.print("Enter due date (yyyy-mm-dd): ");
        String due_Date = sc.nextLine();

        Task task = new Task(getNextTaskId(), description, due_Date, false);
        taskList.add(task);
        saveTasksToFile();
    }

    public static void main(String[] args) {
        loadTasksFromFile();
        int choice;

        while (true){
            System.out.println("\nTask Management System");
            System.out.println("1. Add a New Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Update Task Details");
            System.out.println("4. Delete a Task");
            System.out.println("5. Mark Task as Completed");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1:
                    addTask();
                    break;

                case 2:
                    viewAllTasks();
                    break;

                case 3:
                    updateTask();
                    break;

                case 4:
                    deleteTask();
                    break;

                case 5:
                    markTaskCompleted();
                    break;

                case 6:
                    saveTasksToFile();
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please Try Again.");
            }
        }
    }
}

class Task{
    private int taskId;
    private String description;
    private String dueDate;
    private boolean completed;

    public Task(int taskId, String description, String dueDate, boolean completed) {
        this.taskId = taskId;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", completed=" + completed +
                '}';
    }
}
