package bean;

public class Task {
    private Long id;
    private String taskName;
    private String clientName;

    public Task() {}

    public Task(String taskName, String clientName) {
        this.taskName = taskName;
        this.clientName = clientName;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
