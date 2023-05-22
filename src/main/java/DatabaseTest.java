import DatabasesCRUD.AuthorDAO;
import DatabasesCRUD.ProjectDAO;
import DatabasesCRUD.TestDAO;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import static Config.BaseTest.connector;
import static Utils.DataReader.readtestData;


public class DatabaseTest {

    AuthorDAO authorDAO = new AuthorDAO(connector);
    TestDAO testDAO = new TestDAO(connector);
    ProjectDAO projectDAO = new ProjectDAO(connector);


    @Test()
    public void authordatabaseTest() {
        Assert.assertTrue(5==5,"Random Assertion");
    }

    @AfterMethod
    public void addResults(ITestResult result) {
        testDAO.addTestResult(result);
        Assert.assertTrue(testDAO.isNewAddedTestExists(result),"record does not exist");
    }


    @Test()
    public void testTableActionsTest() {
        testDAO.copyTestsUpdate(testDAO.getTestsByPattern(), readtestData("name"), readtestData("methodName"));
        authorDAO.addAuthor(readtestData("name"), readtestData("login"), readtestData("email"));
        projectDAO.addProject(readtestData("name"));
        Assert.assertTrue(authorDAO.isAuthorExist(readtestData("name")), "Author does not exists");
        Assert.assertTrue(testDAO.isTestExist(readtestData("name")), "information didn't update");
    }

    @AfterTest
    public void deleteRecords(){
        authorDAO.deleteAuthor(readtestData("name"));
        projectDAO.deleteProject(readtestData("name"));
        testDAO.deleteAllModifiedTest();
        Assert.assertFalse(testDAO.isTestExist(readtestData("TestName")),"record was not deleted");
        Assert.assertFalse(authorDAO.isAuthorExist(readtestData("name")), "Author was not deleted");
        Assert.assertFalse(testDAO.isTestExist(readtestData("name")), "information was not deleted");
    }
}
