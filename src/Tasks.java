import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tasks {
    private static int lastID=0;

    private int id;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    Tasks(String descritpion){
        id=++lastID;
        this.description=descritpion;
        this.status="todo";
        this.createdAt=LocalDateTime.now();
        this.updatedAt=LocalDateTime.now();
    }

    public Tasks(int id, String description, String status, LocalDateTime createdAt, LocalDateTime upDatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = upDatedAt;
    }

    public int getID(){
        return this.id;
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status=status;
        this.updatedAt=LocalDateTime.now();
    }

    public void setDescription(String description) {
        this.description = description;
        this.updatedAt=LocalDateTime.now();
    }

    public static Tasks fromJson(String json) {
        json = json.replace("{", "").replace("}", "").replace("\"", "");
        String[] parts = json.split(", ");
        int id = Integer.parseInt(parts[0].split(": ")[1]);
        
        lastID=id;
        
        String description = parts[1].split(": ")[1];
        String status = parts[2].split(": ")[1];
        LocalDateTime createdAt = LocalDateTime.parse(parts[3].split(": ")[1].trim(), formatter);
        LocalDateTime updatedAt = LocalDateTime.parse(parts[4].split(": ")[1].trim(), formatter);
        return new Tasks(id, description,status,createdAt,updatedAt);
    }

    @Override
    public String toString() {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");
        jsonBuilder.append("\"ID\": ").append(this.id).append(", ");
        jsonBuilder.append("\"Description\": ").append("\"").append(this.description).append("\", ");
        jsonBuilder.append("\"Status\": ").append("\"").append(this.status).append("\", ");
        jsonBuilder.append("\"Created At\": ").append("\"").append(this.createdAt.format(formatter)).append("\", ");
        jsonBuilder.append("\"Updated At\": ").append("\"").append(this.updatedAt.format(formatter)).append("\"");
        jsonBuilder.append("}");

        return jsonBuilder.toString();
    }
}
