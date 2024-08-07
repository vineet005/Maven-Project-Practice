import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EmployeeJsonUpdater {
    public static void main(String[] args) {
        String jsonFilePath = "employees.json";
        String fieldToUpdate = "department";
        String newValue = "Technology";

        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
            JsonArray employeesArray = JsonParser.parseString(jsonContent).getAsJsonArray();

            for (int i = 0; i < employeesArray.size(); i++) {
                JsonObject employeeObject = employeesArray.get(i).getAsJsonObject();
                employeeObject.addProperty(fieldToUpdate, newValue);
            }

            Gson gson = new Gson();
            Files.write(Paths.get(jsonFilePath), gson.toJson(employeesArray).getBytes());
            System.out.println("Field Updated Successfully!");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
