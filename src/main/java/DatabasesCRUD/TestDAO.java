package DatabasesCRUD;

import Config.DatabaseConnector;
import Dto.Test;
import Dto.TestResult;
import org.testng.ITestResult;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Dto.TestResult.getTestObj;
import static aquality.selenium.browser.AqualityServices.getLogger;

public class TestDAO {
    private Connection connection;

    public TestDAO(DatabaseConnector connector) {
        connection = connector.getConnection();
    }

    public List<Test> getTestsByPattern() {
        List<Test> tests = new ArrayList<>();
        String pattern = "11";
        String sql = "SELECT * FROM test WHERE ID LIKE ? LIMIT ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + pattern + "%");
            statement.setInt(2, 10);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("name");
                int statusId = resultSet.getInt("status_id");
                String methodName = resultSet.getString("method_name");
                int projectId = resultSet.getInt("project_id");
                int sessionId = resultSet.getInt("session_id");
                String startTime = resultSet.getString("start_time");
                String endTime = resultSet.getString("end_time");
                String env = resultSet.getString("env");
                String browser = resultSet.getString("browser");

                Test test = new Test(id, name, statusId, methodName, projectId, sessionId, startTime, endTime, env, browser);
                tests.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tests;
    }

    public void copyTestsUpdate(List<Test> tests, String newAuthorname, String newMethodName) {
        String updateSql = "UPDATE test SET name = ?,method_name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
            for (Test test : tests) {
                statement.setString(1, newAuthorname);
                statement.setString(2, newMethodName);
                statement.setInt(3, test.getId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean isTestExist(String tstName) {
        String sql = "SELECT * FROM test WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tstName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                if (name.equals(tstName)) {
                    getLogger().info("Test with name - " + tstName + " exists.");
                    return true;
                }
            }
        } catch (SQLException e) {
            getLogger().error("cant find test" + e);
            e.printStackTrace();
        }
        return false;
    }
    public void addTestResult(ITestResult result) {
        String sql = "INSERT INTO test (name, method_name, start_time,end_time,env,project_id,session_id) VALUES (?, ?, ?, ?, ?, ?,?)";
        TestResult test = getTestObj(result);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, test.getName());
            statement.setString(2, test.getMethodName());
            statement.setString(3, String.valueOf(test.getStartTime()));
            statement.setString(4, String.valueOf(test.getEndTime()));
            statement.setString(5, test.getEnv());
            statement.setInt(6,1);
            statement.setInt(7,1);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean isNewAddedTestExists(ITestResult result) {
        String sql = "SELECT * FROM test WHERE name = ? AND method_name = ?";
        String testName = getTestObj(result).getName();
        String testmethodname = getTestObj(result).getMethodName();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, testName);
            statement.setString(2, testmethodname);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String methodname = resultSet.getString("method_name");
                if (name.equals(testName) && methodname.equals(testmethodname)) {
                    getLogger().info("Test with name - " + testName + " and method name - " + testmethodname + " exists.");
                    return true;
                }
            }
        } catch (SQLException e) {
            getLogger().error("Error finding test: " + e);
            e.printStackTrace();
        }
        return false;
    }
    public void deleteAllModifiedTest() {
        String sql = "DELETE FROM test WHERE id LIKE ?";
        String sql1 = "DELETE FROM test WHERE id > ?";
        int idThreshold = 345;
        String pattern = "11";
        try (PreparedStatement statement = connection.prepareStatement(sql);PreparedStatement statement2 = connection.prepareStatement(sql1)) {
            statement.setString(1, "%" + pattern + "%");
            statement2.setInt(1, idThreshold);
            int rowsAffected = statement.executeUpdate();
            int rowsAffected2 = statement2.executeUpdate();
            getLogger().info(rowsAffected + " row(s) deleted.\n" + rowsAffected2 + " row(s) deleted.\n)");
        } catch (SQLException e) {
            getLogger().error("author was not deleted" + e);
            e.printStackTrace();
        }
    }
}
