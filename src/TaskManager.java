import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class TaskManager {
    private final Path FILE_PATH = Path.of("data/tasks.json");
    private List<Tasks> list;
    TaskManager(){
        // this.list = loadTasks();
        this.list=loadTasks();
    }

    List<Tasks> loadTasks(){
        List<Tasks> stored_tasks = new ArrayList<>();
        if (!Files.exists(FILE_PATH)){
            try {
                Files.createDirectories(Path.of("data"));
                Files.createFile(FILE_PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }
        try {
            String jsonContent = Files.readString(FILE_PATH);
            String[] taskList = jsonContent.replace("[", "").replace("]", "").split("},");
            for (String taskJson : taskList) {
                if (!taskJson.endsWith("}")){
                    taskJson = taskJson + "}";
                    stored_tasks.add(Tasks.fromJson(taskJson));
                } else {
                    stored_tasks.add(Tasks.fromJson(taskJson));
                }
            }
            
        } catch (ArrayIndexOutOfBoundsException e) {
        } catch(Exception e){
            e.printStackTrace();
        }
        return stored_tasks;
    }
    void saveTasks(){

        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i=0;i<list.size();i++){
            sb.append(list.get(i).toString());
            if(i<list.size()-1){
                sb.append(",\n");
            }
        }
        sb.append("\n]");

        String jsonString = sb.toString();

        try {
            Files.writeString(FILE_PATH, jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void add(String descirption){
        Tasks tasks = new Tasks(descirption);
        list.add(tasks);
        System.out.println("Task added successfully (ID: "+tasks.getID()+")");
    }

    void update(String id, String description){
        try {
            int ID = Integer.parseInt(id);
            Tasks task = list.stream().filter(tasks->tasks.getID()==ID).findFirst().orElseThrow();
            task.setDescription(description);
        } catch (NoSuchElementException e) {
            System.out.println("Invalid Task ID.");
        }
    }

    void delete(String id){
        try {
            int ID = Integer.parseInt(id);
            Tasks task = list.stream().filter(tasks->tasks.getID()==ID).findFirst().orElseThrow();
            list.remove(task);
        } catch (NoSuchElementException e) {
            System.out.println("Invalid Task ID.");
        }
    }

    void mark(String id,String status){
        try {
            int ID = Integer.parseInt(id);
            Tasks task = list.stream().filter(tasks->tasks.getID()==ID).findFirst().orElseThrow();
            task.setStatus(status);
        } catch (NoSuchElementException e) {
            System.out.println("Invalid Task ID.");
        }
    }

    void list(){
        for (Tasks tasks : list) {
            System.out.println(tasks);
        }
    }

    void list_filter(String status){
        list.stream().filter(task->task.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList()).stream().forEach(System.out::println);
    }

}
