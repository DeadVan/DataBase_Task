package Config;

import static Utils.DataReader.readConfigData;
import static aquality.selenium.browser.AqualityServices.getLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private Connection connection;

    private static DatabaseConnector instance;

    private DatabaseConnector() {
        getLogger().info("Connecting to the database");
        try {
            connection = DriverManager.getConnection(readConfigData("URL"), readConfigData("USERNAME"), readConfigData("PASSWORD"));
        } catch (SQLException e) {
            getLogger().error("Unable to connect to the database" + e);
            e.printStackTrace();
        }
    }

    public static synchronized DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }

    public Connection getConnection()  {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(readConfigData("URL"), readConfigData("USERNAME"), readConfigData("PASSWORD"));
            }
            return connection;
        }catch (SQLException e) {
            getLogger().error("Unable to get connection " + e);
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        getLogger().info("Closing the database connection");
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                getLogger().error("Unable to close the database connection" + e);
                e.printStackTrace();
            }
        }
    }
}
