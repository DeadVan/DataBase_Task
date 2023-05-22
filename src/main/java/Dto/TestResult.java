package Dto;

import org.testng.ITestResult;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

public class TestResult {
    private String name;
    private String methodName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String env;

    public TestResult(String name,String methodName,
                      LocalDateTime startTime, LocalDateTime endTime, String env) {
        this.name = name;
        this.methodName = methodName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.env = env;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }


    @Override
    public String toString() {
        return "Test{" +
                ", name='" + name + '\'' +
                ", methodId=" + methodName +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", environment='" + env + '\'' +
                '}';
    }
    public static TestResult getTestObj(ITestResult result){
        String methodName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        long time1 = result.getStartMillis();
        LocalDateTime startTime = Instant.ofEpochMilli(time1)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        long time2 = result.getEndMillis();
        LocalDateTime endtime = Instant.ofEpochMilli(time2)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        Calendar calendar = Calendar.getInstance();
        String calendarType = calendar.getCalendarType();
        TestResult test = new TestResult(className, methodName, startTime, endtime, calendarType);
        return test;
    }
}
