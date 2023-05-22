package DatabasesCRUD;

import Config.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static aquality.selenium.browser.AqualityServices.getLogger;

public class ProjectDAO {

    private Connection connection;

    public ProjectDAO(DatabaseConnector connector) {
        connection = connector.getConnection();
    }

    public void addProject(String name) {
        String sql = "INSERT INTO project (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            int rowsAffected = statement.executeUpdate();
            getLogger().info(rowsAffected + " row(s) added.");
        } catch (SQLException e) {
            getLogger().error("author was not added" + e);
            e.printStackTrace();
        }
    }
    public void deleteProject(String name) {
        String sql = "DELETE FROM project WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            int rowsAffected = statement.executeUpdate();
            getLogger().info(rowsAffected + " row(s) deleted.");
        } catch (SQLException e) {
            getLogger().error("author was not deleted" + e);
            e.printStackTrace();
        }
    }
}
