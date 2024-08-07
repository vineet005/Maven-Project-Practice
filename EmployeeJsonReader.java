import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class EmployeeJsonReader {
    public static void main(String[] args) {
        String filePath = "employees.json";

        try (FileReader reader = new FileReader(filePath)){
            Type employeeList = new TypeToken<List<Employee>>() {}.getType();
            List<Employee> employees = new Gson().fromJson(reader, employeeList);
            for (Employee employee: employees)
                System.out.println(employee);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

