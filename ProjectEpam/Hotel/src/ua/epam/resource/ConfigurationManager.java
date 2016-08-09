package ua.epam.resource;

import java.util.ResourceBundle;

/**
 * Created by lomak on 18.01.2016.
 */
public class ConfigurationManager {
    private final static ResourceBundle resourceBundle =
            ResourceBundle.getBundle("ua.epam.resource.config");

    private ConfigurationManager() { }

    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }
}