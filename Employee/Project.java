package Employee;

public class Project {
    private int project_Id;
    private String project_Name;
    private String status;

    public Project(int project_Id, String project_Name, String status) {
        this.project_Id = project_Id;
        this.project_Name = project_Name;
        this.status = status;
    }

    public int getProject_Id() {
        return project_Id;
    }

    public void setProject_Id(int project_Id) {
        this.project_Id = project_Id;
    }

    public String getProject_Name() {
        return project_Name;
    }

    public void setProject_Name(String project_Name) {
        this.project_Name = project_Name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Project{" +
                "project_Id=" + project_Id +
                ", project_Name='" + project_Name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
