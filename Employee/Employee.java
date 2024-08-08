package Employee;

import java.util.List;

public class Employee {
    private int employee_Id;
    private String name;
    private String department;
    private Address address;
    private List<Project> projects;

    public Employee(int employee_Id, String name, String department, Address address, List<Project> projects) {
        this.employee_Id = employee_Id;
        this.name = name;
        this.department = department;
        this.address = address;
        this.projects = projects;
    }

    public int getEmployee_Id() {
        return employee_Id;
    }

    public void setEmployee_Id(int employee_Id) {
        this.employee_Id = employee_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_Id=" + employee_Id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", address=" + address +
                ", projects=" + projects +
                '}';
    }
}
