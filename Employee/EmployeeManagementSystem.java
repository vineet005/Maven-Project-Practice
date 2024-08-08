package Employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagementSystem {
    private static final String JSON_File_Path = "employee.json";
    private static List<Employee> employeeList =new ArrayList<>();

    private static final Scanner sc = new Scanner(System.in);
    private static ObjectMapper objectMapper = new ObjectMapper();

    private static boolean isEmployeeIdUnique(int employeeId){
        return employeeList.stream()
                .noneMatch(e -> e.getEmployee_Id() == employeeId);
    }

    private static Employee findEmployeeById(int employeeId){
        return employeeList.stream()
                .filter(empId -> empId.getEmployee_Id() == employeeId)
                .findFirst()
                .orElse(null);
    }

    private static void saveEmployeesToFile(){
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(JSON_File_Path), employeeList.toArray(new Employee[0]));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void addEmployees(){
        System.out.println("Enter EmployeeId: ");
        int employee_Id = sc.nextInt();
        sc.nextLine();
        if (isEmployeeIdUnique(employee_Id)){
            System.out.print("Enter employee name: ");
            String name = sc.nextLine();
            System.out.print("Enter department: ");
            String department = sc.nextLine();

            System.out.println("Enter address details:");
            System.out.print("Street: ");
            String street = sc.nextLine();
            System.out.print("City: ");
            String city = sc.nextLine();
            System.out.print("Zipcode: ");
            String zipcode = sc.nextLine();

            Address address = new Address(street, city, zipcode);

            List<Project> projects = new ArrayList<>();
            System.out.println("Enter Number of Projects: ");
            int num_Projects = sc.nextInt();
            sc.nextLine();

            for (int i=0; i<num_Projects; i++){
                System.out.print("Enter project ID: ");
                int projectId = sc.nextInt();
                sc.nextLine(); // Consume newline left-over
                System.out.print("Enter project name: ");
                String projectName = sc.nextLine();
                System.out.print("Enter project status: ");
                String status = sc.nextLine();

                projects.add(new Project(projectId, projectName, status));
                System.out.println("Project Added Successfully...");
            }

            Employee employee = new Employee(employee_Id, name, department, address, projects);
            employeeList.add(employee);
            saveEmployeesToFile();
            System.out.println("Employee added successfully.");
        }
        else {
            System.out.println("Employee ID already exists. Please choose a unique ID.");

        }
    }

    private static void viewAllEmployees(){
        if (employeeList.isEmpty()){
            System.out.println("No Employees Found!");
            return;
        }
        employeeList.forEach(System.out::println);
    }

    private static void updateEmployeeDetails(){
        System.out.println("Enter EmployeeId to update details of that employee: ");
        int employeeId = sc.nextInt();
        sc.nextLine();

        Employee employee = findEmployeeById(employeeId);
        if (employee == null){
            System.out.println("Employee Not Found!");
            return;
        }
        System.out.print("Enter new name: ");
        String name = sc.nextLine();
        System.out.print("Enter new department: ");
        String department = sc.nextLine();

        System.out.println("Enter new address details:");
        System.out.print("Street: ");
        String street = sc.nextLine();
        System.out.print("City: ");
        String city = sc.nextLine();
        System.out.print("Zipcode: ");
        String zipcode = sc.nextLine();

        Address newAddress = new Address(street, city, zipcode);
        employee.setName(name);
        employee.setDepartment(department);
        employee.setAddress(newAddress);

        saveEmployeesToFile();
        System.out.println("Employee details updated successfully...");
    }

    private static void deleteEmployee(){
        System.out.println("Enter EmployeeId to delete the Employee from the system: ");
        int employeeId = sc.nextInt();
        Employee employee = findEmployeeById(employeeId);

        if (employee == null){
            System.out.println("Employee Not Found!");
            return;
        }
        employeeList.remove(employee);
        saveEmployeesToFile();
        System.out.println("Employee Details Deleted Successfully...");
    }

    private static Employee searchEmployeeById(){
        System.out.println("Write the Employee's Id you want to search: ");
        int employeeId = sc.nextInt();
        Employee employee = findEmployeeById(employeeId);

        if (employee == null){
            System.out.println("Employee Not Found!");
            return null;
        }
        return employeeList.stream()
                .filter(emp -> emp.getEmployee_Id() == employeeId)
                .findFirst()
                .orElse(null);

    }

    private static void loadEmployeesFromFile(){
        try {
            File file = new File(JSON_File_Path);
            if (file.exists()){
                Employee[] employees = objectMapper.readValue(file, Employee[].class);
                employeeList = new ArrayList<>(List.of(employees));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        loadEmployeesFromFile();
        int choice;

        while (true){
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add a New Employee");
            System.out.println("2. View All Employee");
            System.out.println("3. Update Employee Details");
            System.out.println("4. Delete an Employee");
            System.out.println("5. Search For an Employee");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1:
                    addEmployees();
                    break;

                case 2:
                    viewAllEmployees();
                    break;

                case 3:
                    updateEmployeeDetails();
                    break;

                case 4:
                    deleteEmployee();
                    break;

                case 5:
                    searchEmployeeById();
                    break;

                case 6:
                    saveEmployeesToFile();
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice. Please Try Again.");
            }
        }
    }
}
