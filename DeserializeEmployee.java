import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DeserializeEmployee {
    public static void main(String[] args) {
        String filePath = "employees.json";

        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
            List<Employee> employees = deserializeEmployees(jsonContent);

            for (Employee employee: employees)
                System.out.println(employee);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static List<Employee> deserializeEmployees(String jsonContent){
        try {
            Type employeeList = new TypeToken<List<Employee>>(){}.getType();
            return new Gson().fromJson(jsonContent, employeeList);
        }catch (JsonSyntaxException e){
            throw new IllegalArgumentException("Invalid JSON format", e);
        }
    }
}

