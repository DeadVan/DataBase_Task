package Dto;


public class Test {
    private int id;
    private String name;
    private int statusId;
    private String methodName;
    private int projectId;
    private int sessionId;
    private String startTime;
    private String endTime;
    private String env;
    private String browser;

    public Test(int id, String name, int statusId, String methodName, int projectId, int sessionId,
                String startTime, String endTime, String env, String browser) {
        this.id = id;
        this.name = name;
        this.statusId = statusId;
        this.methodName = methodName;
        this.projectId = projectId;
        this.sessionId = sessionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.env = env;
        this.browser = browser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + statusId + '\'' +
                ", methodId=" + methodName +
                ", projectId=" + projectId +
                ", sessionId=" + sessionId +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", environment='" + env + '\'' +
                ", browser='" + browser + '\'' +
                '}';
    }
}

