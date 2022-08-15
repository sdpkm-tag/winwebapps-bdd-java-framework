package com.winapps.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    public static Properties properties;
    private static final String CONFIG_FILE_PATH = "./src/test/resources/config/config.properties";

    /**
     * This method is used to load the properties form "config.properties" file.
     *
     * @return It returns properties object to the caller.
     */
    public Properties readProperty() {

        properties = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(CONFIG_FILE_PATH);
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file can not be found!");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

}
