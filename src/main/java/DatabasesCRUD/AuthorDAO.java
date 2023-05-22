package DatabasesCRUD;

import Config.DatabaseConnector;
import Utils.DatabaseUtility;

import java.sql.*;

import static aquality.selenium.browser.AqualityServices.getLogger;

public class AuthorDAO extends DatabaseUtility {
    private Connection connection;
    public AuthorDAO(DatabaseConnector connector) {
        connection = connector.getConnection();
    }

    public void addAuthor(String name, String login, String email) {
        String sql = "INSERT INTO author (name, login, email) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, login);
            statement.setString(3, email);
            int rowsAffected = statement.executeUpdate();
            getLogger().info(rowsAffected + " row(s) added.");
        } catch (SQLException e) {
            getLogger().error("author was not added" + e);
            e.printStackTrace();
        }
    }

    public void deleteAuthor(String name) {
        String sql = "DELETE FROM author WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            int rowsAffected = statement.executeUpdate();
            getLogger().info(rowsAffected + " row(s) deleted.");
        } catch (SQLException e) {
            getLogger().error("author was not deleted" + e);
            e.printStackTrace();
        }
    }

    public boolean isAuthorExist(String withName) {
        String sql = "SELECT * FROM author WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, withName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                if (name.equals(withName)) {
                    getLogger().info(" With name - " + withName + ". = author exists");
                    return true;
                }
            }
        } catch (SQLException e) {
            getLogger().error("cant find author" + e);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void resetAutoIncrementValue() {
        getLogger().info("resetting Auto Increment Value");
        String sql = "ALTER TABLE author AUTO_INCREMENT = 1";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            getLogger().error("did not reset Auto Increment Value");
            e.printStackTrace();
        }
    }

    @Override
    public boolean isTableEmpty() {
        getLogger().info("checking if table is empty");
        String sql = "SELECT COUNT(*) FROM author";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int rowCount = resultSet.getInt(1);
                return rowCount == 0;
            }
        } catch (SQLException e) {
            getLogger().error("cant find table" + e);
            e.printStackTrace();
        }
        return true;
    }
}


