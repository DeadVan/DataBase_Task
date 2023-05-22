package Utils;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;

public class DataReader {
    private static ISettingsFile testDataReader = new JsonSettingsFile("test_data.json");
    private static ISettingsFile configReader = new JsonSettingsFile("Config.json");
    public static String readtestData(String value){
        return testDataReader.getValue("/"+value).toString();
    }

    public static String readConfigData(String value){
        return configReader.getValue("/"+value).toString();
    }

}
