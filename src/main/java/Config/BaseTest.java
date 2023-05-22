package Config;


import org.testng.annotations.AfterTest;



public class BaseTest {
    public static DatabaseConnector connector = DatabaseConnector.getInstance();
    @AfterTest
    public void closeDriver(){
        connector.closeConnection();
    }
}
